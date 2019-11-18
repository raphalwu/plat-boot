package com.xxx.plat.base.twitter;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowflakeIDGenerator {
    private static Logger log = LoggerFactory.getLogger(SnowflakeIDGenerator.class);
    private final long datacenterIdBits = 10L;
    private final long maxDatacenterId = 1023L;
    private final long sequenceBits = 12L;
    private final long datacenterIdShift = 12L;
    private final long timestampLeftShift = 22L;
    private final long sequenceMask = 4095L;
    private final long twepoch = 1526764836666L;
    private long datacenterId;
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    public SnowflakeIDGenerator(long datacenterId) {
        if (datacenterId == 0L) {
            try {
                this.datacenterId = this.getDatacenterId();
            } catch (UnknownHostException | NullPointerException | SocketException var5) {
                log.warn("SNOWFLAKE: could not determine machine address; using random datacenter ID");
                Random rnd = new Random();
                this.datacenterId = (long)(rnd.nextInt(1023) + 1);
            }
        } else {
            this.datacenterId = datacenterId;
        }

        if (this.datacenterId > 1023L || datacenterId < 0L) {
            log.warn("SNOWFLAKE: datacenterId > maxDatacenterId; using random datacenter ID");
            Random rnd = new Random();
            this.datacenterId = (long)(rnd.nextInt(1023) + 1);
        }

        log.info("SNOWFLAKE: initialised with datacenter ID {}", this.datacenterId);
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = System.currentTimeMillis(); timestamp <= lastTimestamp; timestamp = System.currentTimeMillis()) {
            ;
        }

        return timestamp;
    }

    protected long getDatacenterId() throws SocketException, UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface network = null;
        Enumeration en = NetworkInterface.getNetworkInterfaces();

        while(en.hasMoreElements()) {
            NetworkInterface nint = (NetworkInterface)en.nextElement();
            if (!nint.isLoopback() && nint.getHardwareAddress() != null) {
                network = nint;
                break;
            }
        }

        byte[] mac = network.getHardwareAddress();
        Random rnd = new Random();
        byte rndByte = (byte)(rnd.nextInt() & 255);
        long id = (255L & (long)mac[mac.length - 1] | 65280L & (long)rndByte << 8) >> 6;
        return id;
    }

    public synchronized long getId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < this.lastTimestamp) {
            log.warn("Clock moved backwards. Refusing to generate id for {} milliseconds.", this.lastTimestamp - timestamp);

            try {
                Thread.sleep(this.lastTimestamp - timestamp);
            } catch (InterruptedException var5) {
                ;
            }
        }

        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & 4095L;
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        this.lastTimestamp = timestamp;
        long id = timestamp - 1526764836666L << 22 | this.datacenterId << 12 | this.sequence;
        if (id < 0L) {
            log.warn("ID is smaller than 0: {}", id);
        }

        return id;
    }
}
