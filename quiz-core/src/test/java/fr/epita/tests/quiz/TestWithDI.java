package fr.epita.tests.quiz;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.Configuration;
import fr.epita.quiz.services.QuestionDAOWithDI;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class TestWithDI {

   // @Inject
   // @Named("testStringInjection")
   // String testFromDI;

    @Inject
    @Named("test")
    Configuration configuration;


    @Inject
    QuestionDAOWithDI daoWithDI;


    @Inject
    DataSource ds;

    @BeforeAll
    public static void setup(){
        System.setProperty("conf.location", "src/test/resources/conf.properties");
    }


    @BeforeEach
    public void dbSetup() throws SQLException {
        Connection connection = this.ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE QUESTIONS (id integer auto_increment not null, title varchar(255) not null)");
        preparedStatement.execute();

    }

   // @Test
    //public void firstTestFromDI() {
      //  Assertions.assertNotNull(testFromDI);
    //}


    @Test
    @DisplayName("When a question is inserted then it should be found in database")
    public void testDAOCreation() throws SQLException {
        //given
        Question question = new Question();
        question.setTitle("test question");
        //provided the dao in the injection context

        //when
        this.daoWithDI.create(question);

        //then
        PreparedStatement preparedStatement = ds.getConnection().prepareStatement("SELECT count(*) as cnt from questions");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int cnt = resultSet.getInt("cnt");

        assertThat(cnt).isEqualTo(1);

        //cleanup


    }




}
