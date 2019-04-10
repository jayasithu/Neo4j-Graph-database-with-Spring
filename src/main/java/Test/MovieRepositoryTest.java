package Test;

import com.example.demo.model.Movie;
import com.example.demo.model.Person;
import com.example.demo.model.Role;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        Movie matrix = new Movie("The Matrix", 1999, "Welcome to the Real World");

        movieRepository.save(matrix);

        Person keanu = new Person("Keanu Reeves", 1964);

        personRepository.save(keanu);

        Role neo = new Role(matrix, keanu);
        neo.addRoleName("Neo");

        matrix.addRole(neo);

        movieRepository.save(matrix);
    }

    @Test
    public void testFindByTitle() {

        String title = "The Matrix";
        Movie result = movieRepository.findByTitle(title);
        assertNotNull(result);
        assertEquals(1999, result.getReleased());
    }

    @Test
    public void testFindByTitleContaining() {
        String title = "*Matrix*";
        Collection<Movie> result = movieRepository.findByTitleLike(title);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGraph() {
        Collection<Movie> graph = movieRepository.graph(5);

        assertEquals(1, graph.size());

        Movie movie = graph.iterator().next();

        assertEquals(1, movie.getRoles().size());

        assertEquals("The Matrix", movie.getTitle());
        assertEquals("Keanu Reeves", movie.getRoles().iterator().next().getPerson().getName());
    }








}
