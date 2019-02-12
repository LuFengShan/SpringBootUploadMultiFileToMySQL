SpringBoot将多个文件上传到MySQL示例
> * 结构 ： SpringBoot + Thymeleaf + Spring JPA Auditing
> * Java 8 + Maven 3.6.1 + SpringBoot + Thymeleaf + Bootstrap 4
## @CreatedDate
表示该字段为创建时间时间字段，在这个实体被insert的时候，会设置值

## @CreatedBy
表示该字段为创建人，在这个实体被insert的时候，会设置值

## @LastModifiedDate
表示该字段为最后一次的修改时间时间字段，在这个实体被修改的时候，会设置值

## @LastModifiedBy
表示该字段为修改人，在这个实体被修改的时候，会设置值

#如何使用审计功能
> 1. 首先申明实体类，需要在类上加上注解@EntityListeners(AuditingEntityListener.class)
> 2. 其次在application启动类中加上注解EnableJpaAuditing，
> 3. 同时在需要的字段上加上@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy等注解。