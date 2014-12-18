///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import oracle.ons.ONS;
//import oracle.ons.SenderThread;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.ReflectionUtils;
//
//import javax.management.MBeanServer;
//import javax.management.ObjectName;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import java.lang.management.ManagementFactory;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.List;
//
//public class CleanUpListener implements ServletContextListener {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        // do nothing
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        shutdownOns();
//        deregisterJdbcDrivers();
//    }
//
//    @SuppressWarnings("unchecked")
//    private void shutdownOns() {
//        logger.info("Shutting down ONS");
//        final Method getRunningONS = ReflectionUtils.findMethod(ONS.class, "getRunningONS");
//        final Method shutdown = ReflectionUtils.findMethod(ONS.class, "shutdown");
//        ReflectionUtils.makeAccessible(getRunningONS);
//        ReflectionUtils.makeAccessible(shutdown);
//        final ONS ons = (ONS) ReflectionUtils.invokeMethod(getRunningONS, null);
//        if (ons == null) {
//            return;
//        }
//        
//        ReflectionUtils.invokeMethod(shutdown, ons);
//
//        final Field senders = ReflectionUtils.findField(ONS.class, "senders");
//        ReflectionUtils.makeAccessible(senders);
//        final List<SenderThread> senderThreads = (List<SenderThread>) ReflectionUtils.getField(senders, ons);
//        if (senderThreads == null) {
//            return;
//        }
//        
//        final Method stopThread = ReflectionUtils.findMethod(SenderThread.class, "stopThread");
//        ReflectionUtils.makeAccessible(stopThread);
//        for (SenderThread senderThread : senderThreads) {
//            ReflectionUtils.invokeMethod(stopThread, senderThread);
//        }
//    }
//    
//    private void deregisterJdbcDrivers() {
//        logger.info("Deregistering JDBC Drivers");
//        final Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
//        final List<Driver> drivers = new ArrayList<Driver>();
//        while (driverEnumeration.hasMoreElements()) {
//            drivers.add(driverEnumeration.nextElement());
//        }
//
//        for (Driver driver : drivers) {
//            if (driver.getClass().getClassLoader() != getClass().getClassLoader()) {
//                logger.debug("Not deregistering {} as it does not originate from this webapp", driver.getClass().getName());
//                continue;
//            }
//            try {
//                DriverManager.deregisterDriver(driver);
//                logger.debug("Deregistered JDBC driver '{}'", driver.getClass().getName());
//                if ("oracle.jdbc.OracleDriver".equals(driver.getClass().getName())) {
//                    deregisterOracleDiagnosabilityMBean();
//                }
//            } catch (Throwable e) {
//                logger.error("Deregistration error", e);
//            }
//        }
//    }
//
//    private void deregisterOracleDiagnosabilityMBean() {
//        final ClassLoader cl = Thread.currentThread().getContextClassLoader();
//        try {
//            final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
//            final Hashtable<String, String> keys = new Hashtable<String, String>();
//            keys.put("type", "diagnosability");
//            keys.put("name", cl.getClass().getName() + "@" + Integer.toHexString(cl.hashCode()).toLowerCase());
//            mbs.unregisterMBean(new ObjectName("com.oracle.jdbc", keys));
//            logger.info("Deregistered OracleDiagnosabilityMBean");
//        } catch (javax.management.InstanceNotFoundException e) {
//            logger.debug("Oracle OracleDiagnosabilityMBean not found", e);
//        } catch (Throwable e) {
//            logger.error("Oracle JMX unregistration error", e);
//        }
//    }
//}