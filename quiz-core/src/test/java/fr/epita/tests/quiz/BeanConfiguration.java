package fr.epita.tests.quiz;

import fr.epita.quiz.services.QuestionDAOWithDI;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;


//configure DI for the application: https://app.diagrams.net/?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=dependency-injection.html#R7VpLk9o4EP41VCWHmbJsY%2BPjQCaPqk1tqjjs5qjBwjgRliPLPPLr07LkFzIsD5PJDsMF1Gq1Wt3qr1tdDJzJcvOB43TxmYWEDmwr3AycdwPbRo7nwpekbBXF9x1FiHgcaqaaMI1%2FEk20NDWPQ5K1GAVjVMRpmzhjSUJmokXDnLN1m23OaHvXFEfEIExnmJrUf%2BJQLBR1ZPs1%2FSOJo0W5M%2FICNfOEZ98jzvJE7zewnXnxUdNLXMrSB80WOGTrBsl5HDgTzphQv5abCaHStqXZ1Lr3e2YrvTlJxFELtB4rTHNSquxRWDt%2BkuqJrTaJ9yOXOo3nLBF3WeGwB2BAQbqpJ%2BFXpL%2BVCP4q4yXL%2BD9SntN2E4qzDHBNi4KwfOpXRbu12C6AiMhQt2B6vYgFmaZ4JmfXANxAW4glhRGCn5ng7DuZMMp4sdoJPN%2FBXjVTAqGt1dGojQI9bqy0io%2Bkx5Q26ASFQ%2BJXmq4IF2SzF7lQhYeQZwhbEsG3wKIXuK6GLp1iAITVeF0DNmiiaIsGWLsjTcQ6SUSV7Boo4YfGyj24iQxjkxBSiB4yLhYsYgmmjzV1PMv5qvCGNHfbNw0%2FkE0s%2FpXk%2BwCV46%2FF2EVDPf5CeAw6E66Xg834Vi0alsOveu4bEWKrnYVzwYBUq%2FcXY6ne91ifqoPL0x72HBiH5XymuWydxTGPiOYKuv3LCcUiXrWlX%2BIr20hxn5JvUDXELAHyBI5JNuKM%2BCsCbycE33wmS8a3b3sSZ14ySqEGkpcJKodUEmeU5eGx0X0ohnuISdtvx6RnGSGJgo6QRE4PIekYbu6I0cp8%2F2EunKWqrpzHGxmkO%2FZzuvDSsjxPod5u5DTopcW9cqyVQ31AYtv6TjA0zD%2FssP6wB%2BN7vwEOS%2FC7BNjsLvdcBGzuhSiml35hMWxSR9LIafkSIbstQgGpXlX76YFzvG2wpZIh278Psoed%2B9RuVxLrS1Cd8ah74R7E3p5QsmcIn0JyxRQYw6vBeAnecLcwpYSyiOMlMKaNvN6aayT8k%2Bu46oKfX8f1D072yAQn5HegU8l3CTr5xi20byw1IKtt%2FsDMzNdKDYFh%2FDKqwnjVGVYymO8gAqNExRUlcwlZxT20TnqsZSlOenjynS9iJl98MPsw8CtxXc8%2BoCldDXJhpDb1Buy2%2Fw3%2Far0jRLiWSrM3Y7Sp4HESFXvyJX6xxzwYFi%2FomP67E8%2B1p75KmDgio9dN%2B79zQeOE%2FOGV1GinlHLNzhfqyubI66PzZbZTnBsrpoZe2wHVI%2Bo3VFPIbHO4N2Z%2Bf6eWdS3z%2Fl%2FN%2FEPD%2FHnR0b9S96Pv5i3FT4SOK7wrWRImQe%2FMDgjSF%2FCI3q72oHVvOU67wXHnasue2zgpWdh8nhFh%2BPS0tgUy%2Bxbn5c7D%2BdEo%2BNqPhvHzPRpOKjyutlvzdJIbF8UIiLMSspavqjdvn9cczf3wPdz%2B90X5%2BSep9Swl41GKvRZ5JxR5rnfFIg%2BG9V9OFFzW%2F%2BtxHn8B
@Configuration
public class BeanConfiguration {


    @Bean("defaultString")
    public String getTestStringValue(){
        return "testFromDI";
    }

    @Bean("testStringInjection")
    public String getTestStringValue2(){
        return "testFromDI";
    }

    @Bean("test")
    public fr.epita.quiz.services.Configuration configuration(){
        return fr.epita.quiz.services.Configuration.getInstance();
    }

    @Bean
    QuestionDAOWithDI questionDAOWithDI(){
        return new QuestionDAOWithDI();
    }


    @Bean
    DataSource dataSource(@Autowired fr.epita.quiz.services.Configuration configuration){
        return new DriverManagerDataSource(configuration.getDBUrl());
    }


    @Bean
    LocalSessionFactoryBean getSessionFactory(@Autowired DataSource ds){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("fr.epita.quiz.datamodel");
        sessionFactoryBean.setDataSource(ds);
        Properties extraproperties = new Properties();
        extraproperties.put("hibernate.hbm2ddl.auto", "update");
        extraproperties.put("hibernate.show_sql", "true");
        sessionFactoryBean.setHibernateProperties(extraproperties);
        return sessionFactoryBean;
    }
}
