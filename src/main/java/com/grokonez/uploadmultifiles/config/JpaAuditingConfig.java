package com.grokonez.uploadmultifiles.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
 
@Configuration
// 开启审计功能
@EnableJpaAuditing
public class JpaAuditingConfig {}