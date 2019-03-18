package util;

import entytis.Category;
import entytis.Pet;
import entytis.Status;
import entytis.Tag;

import java.util.ArrayList;

public class PetGenerator {
    public Pet generatePet(int id, String name) {
        ArrayList<String> photoUrls = new ArrayList<String>();
        photoUrls.add("randomPhotoURL");
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag(1, "myTag"));
        return new Pet(id, new Category(1, "myCategory"), name, photoUrls, tags, Status.available);
    }
}
