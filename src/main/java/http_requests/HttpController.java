package http_requests;

import com.google.gson.Gson;
import entytis.Pet;
import entytis.Status;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import util.GetFileFromPath;
import util.GsonController;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HttpController {
    GsonController gson = new GsonController();

    public String postPet(Pet pet) {
        String url = "https://petstore.swagger.io/v2/pet";
        String json = gson.toJson(pet);
        try {
            org.jsoup.nodes.Document document = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .header("Content-Type", "application/json")
                    .requestBody(json)
                    .post();
            return document.select("body").text();
        } catch (IOException e) {
            System.out.println("Error in trying to post a pet");
            e.printStackTrace();
        }
        return null;
    }

    public Pet getPetByID(int id){
        String url = "https://petstore.swagger.io/v2/pet/" + id;
        try {
            String jsonString = Jsoup.connect(url).ignoreContentType(true).get().select("body").text();
            return (Pet) gson.fromJson(jsonString, Pet.class);
        } catch (IOException e) {
            System.out.println("Pet with this ID was not found");
        }
        return null;
    }

    public String updatePetWithID(int id, String newName, Status status){
        String url = "https://petstore.swagger.io/v2/pet/" + id;
        try {
            return Jsoup.connect(url)
                    .ignoreContentType(true)
                    .data("name", newName)
                    .data("status", status.toString())
                    .post().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletePetWithID(int id){
        String url = "https://petstore.swagger.io/v2/pet/" + id;
        try {
            Jsoup.connect(url)
                    .ignoreContentType(true)
                    .method(Connection.Method.DELETE)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPhotoToPetWithID(int id, String filePath, String additionalData){
        String url = "https://petstore.swagger.io/v2/pet/" + id + "/uploadImage";
        File file = new File(filePath);
        try {
            Document response = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .data("additionalMetadata", additionalData)
                    .data("file", file.getName(), new FileInputStream(filePath))
                    .post();
            System.out.println(response.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
