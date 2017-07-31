package com.resit.sdgp.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //we will use these constants later to pass the artist name and id to another activity
    public static final String AUTHOR_NAME = "com.resit.sdgp.resitsdgp.authorname";
    public static final String ARTHOR_ID = "com.resit.sdgp.resitsdgp.authorid";

    //view objects
    EditText AName;
    Spinner spinnerbooks;
    Button addAuthor;
    ListView listAuthors;

    //a list to store all the artist from firebase database
    List<Author> authors;

    //our database reference object
    DatabaseReference databaseAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        /**   FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference myRef = database.getReference("message");

         myRef.setValue("Hello, World!");**/


        //getting the reference of artists node
        databaseAuthor = FirebaseDatabase.getInstance().getReference("authors").push();

        //getting views
        AName = (EditText) findViewById(R.id.AName);
        spinnerbooks = (Spinner) findViewById(R.id.spinnerbooks);
        listAuthors = (ListView) findViewById(R.id.listAuthors);
        addAuthor = (Button) findViewById(R.id.addAuthor);

        //list to store authors
        authors = new ArrayList<>();


        //adding an onclicklistener to button
        addAuthor.setOnClickListener(new View.OnClickListener() {
           // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").push();

            @Override
            public void onClick(View view) {
                //calling the method addArthor()
                //the method is defined below
                //this method is actually performing the write operation
                addAuthor();

            }
        });
    }



    /*
    * This method is saving a new artist to the
    * Firebase Realtime Database
    * */
    private void addAuthor() {
        //getting the values to save
        String name = AName.getText().toString().trim();
        String genre = spinnerbooks.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseAuthor.push().getKey();

            //creating an Artist Object
            Author author = new Author(id, name, genre);

            //Saving the Artist
            databaseAuthor.child(id).setValue(author);

            //setting edittext to blank again
            AName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Author added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
