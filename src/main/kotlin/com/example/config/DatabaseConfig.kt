package com.example.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

object DatabaseConfig {
    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/sample_request_db"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = "root@localhost"
            password = "12345"
            maximumPoolSize = 10
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
    }
}
