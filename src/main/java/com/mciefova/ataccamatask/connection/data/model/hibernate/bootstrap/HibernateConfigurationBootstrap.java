package com.mciefova.ataccamatask.connection.data.model.hibernate.bootstrap;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({ "classpath:persistenceContext.xml" })
public class HibernateConfigurationBootstrap {
}
