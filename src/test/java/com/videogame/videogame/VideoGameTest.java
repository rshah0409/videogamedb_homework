package com.videogame.videogame;

import com.videogame.VideoGamePojo;
import com.videogame.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class VideoGameTest extends TestBase {
    public Random randomGenerator = new Random();
    public int randomInt = randomGenerator.nextInt( 1000 );

    @Test
    //GET ALL
    public void getAllVideoGamesInfo() {
        Response response =
                given()
                        .accept( "application/json" )
                        .when()
                        .get();
        response.then().statusCode( 200 );
        response.prettyPrint();
    }

    @Test
    //GET BY ID
    public void getVideoGameById() {
        Response response =
                given()
                        .accept( "application/json" )
                        .when()
                        .get( "/4" );
        response.then().statusCode( 200 );
        response.prettyPrint();
    }

    @Test
    //GET WITH PARAMETERS
    public void getVideoGameWithParametes() {
        HashMap<String, Object> qParams = new HashMap<>();
        qParams.put( "category", "Platform" );
        qParams.put( "limit", 2 );
        Response response =
                given()
//                        .queryParam( "reviewScore",90  )
//                        .queryParam( "limit",2 )

                        .queryParams( qParams )
                        .accept( "application/json" )
                        .when()
                        .get();
        response.prettyPrint();
    }

    @Test
    //POST
    public void createNewVideoGame() {
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId( randomInt );
        videoGamePojo.setName( "Car Games" );
        videoGamePojo.setReleaseDate( "2021-08-15T09:14:42.993Z" );
        videoGamePojo.setReviewScore( 90 );
        videoGamePojo.setCategory( "Kids" );
        videoGamePojo.setRating( "PG" );
        videoGamePojo.setPrice( 34 );

        Response response =
                given()
                        //  .accept("application/json")
                        .header( "Content-Type", "application/json" )
                        .body( videoGamePojo ).accept( "application/json" )
                        .when()
                        .post();
        response.then().statusCode( 200 );
        response.prettyPrint();


    }

    @Test
    //PUT
    public void updateVideoGameById() {
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId( 4 );
        videoGamePojo.setName( "Hit Man 2" );
        videoGamePojo.setReleaseDate( "2021-07-15T19:24:01.924Z" );
        videoGamePojo.setReviewScore( 99 );
        videoGamePojo.setCategory( "Fight" );
        videoGamePojo.setRating( "PG-12" );

        Response response =
                given()
                        .accept( "application/json" )
                        .header( "Content-Type", "application/json" )
                        .body( videoGamePojo )
                        .when()
                        .put( "/4" );
        response.then().statusCode( 200 );
        response.prettyPrint();

    }

    @Test
    //DELETE
    public void deleteVideoGameId() {
        Response response =
                given()
                        .pathParam( "id", 9 )
                        .when()
                        .delete( "/{id}" );
        response.then().statusCode( 200 );
        response.prettyPrint();


    }


}
