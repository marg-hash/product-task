
public class SchedulerConfig {

	@Configuration
	public class SchedulerConfig {
	    @Autowired
	    private DataSource dataSource;

	    @Autowired
	    private ApplicationContext applicationContext;

	    @Autowired
	    private QuartzProperties quartzProperties;

	    @Bean
	    public SchedulerFactoryBean schedulerFactoryBean() {

	        SchedulerJobFactory jobFactory = new SchedulerJobFactory();
	        jobFactory.setApplicationContext(applicationContext);

	        Properties properties = new Properties();
	        properties.putAll(quartzProperties.getProperties());

	        SchedulerFactoryBean factory = new SchedulerFactoryBean();
	        factory.setOverwriteExistingJobs(true);
	        factory.setDataSource(dataSource);
	        factory.setQuartzProperties(properties);
	        factory.setJobFactory(jobFactory);
	        return factory;
	    }	
	}
}
