import my.library.Book;
import my.library.Library;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

/**
* ESTEVES Mathéo
* GAUTIER Romain
*/
public class ListeMoiCa{
    public static void main(String[] args) throws JAXBException {
        System.out.println("Processing "+ args[0]);

        // Get a stream from path
        InputStream stream = null;
        try {
            stream = new FileInputStream(args[0]);
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }


        Library uneListeDeBooks = null;

        JAXBContext jc = null;

        try {
            jc = JAXBContext.newInstance("my.library");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            uneListeDeBooks = (Library)
                    unmarshaller.unmarshal(stream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println("La base contient " + uneListeDeBooks.getBook().size() + "livre(s) \nisbn  disponibilité langue  titre  premier-auteur id nombre-de-personnages");

        Iterator<Book> uneListeDeBooksIterator = uneListeDeBooks.getBook().iterator();
        while (uneListeDeBooksIterator.hasNext()) {
            Book unBook = uneListeDeBooksIterator.next();
            System.out.println(
                unBook.getIsbn() + " " +
                 (unBook.isAvailable() ? "oui" : "non") + " " 
                 + unBook.getTitle().getLang() + " " 
                 + unBook.getTitle().getValue() + " " 
                 + unBook.getAuthor().get(0).getName() 
                 + " (" + unBook.getAuthor().get(0).getId() + ") " 
                 + unBook.getCharacter().size());
        }
    }
}
