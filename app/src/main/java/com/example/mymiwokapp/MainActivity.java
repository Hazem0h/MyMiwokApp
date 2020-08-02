package com.example.mymiwokapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecordingMonitor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ViewPropertyAnimator;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener,
        MediaPlayer.OnCompletionListener {

    public static final int NUM_CATEGORIES = 4;
    //we will create a bunch of constants to identify the type of fragment created
    public static final int NUM_FRAG = 0;
    public static final int FAMILY_FRAG = 1;
    public static final int COLOR_FRAG = 2;
    public static final int PHRASES_FRAG = 3;

    //We will also initialize our data here
    protected static ArrayList<Word> numbersList = new ArrayList<>();
    protected static ArrayList<Word> familyList = new ArrayList<>();
    protected static ArrayList<Word> colorList = new ArrayList<>();
    protected static ArrayList<Word> phrasesList = new ArrayList<>();


    public MainActivity(){

        //the data sources will be populated in the activity constructor
        //why? Becuase populating them in onCreate may duplicate the population
        //the activity can be destroyed but the activity object itself wasn't destroyed
        //so when onCreate is called again, data is duplicated.
        //So data population must be independent of activity state
        super();
        populateNumList();
        populateFamilyList();
        populateColorList();
        populatePhrasesList();
    }
    public MediaPlayer getPlayer() {
        return player;
    }

    //audio management
    private MediaPlayer player = null;

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    private AudioManager audioManager;

    public AudioManager getAudioManager() {
        return audioManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///
        //ViewPager and TabLayout stuff
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
         // ViewPager.setAdapter
        viewPager.setAdapter(new PageFragmentAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                stopPlayer();
            }

            @Override
            public void onPageSelected(int position) {
                stopPlayer();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                stopPlayer();
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        audioManager = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);
    }
    private void populateNumList(){
        numbersList.add(new Word("one", "lutti", R.raw.number_one,R.drawable.number_one));
        numbersList.add(new Word("two", "otiiko", R.raw.number_two, R.drawable.number_two));
        numbersList.add(new Word("three", "tolooksu", R.raw.number_three, R.drawable.number_three));
        numbersList.add(new Word("four", "oyyisa",R.raw.number_four,R.drawable.number_four));
        numbersList.add(new Word("five", "massokka", R.raw.number_five, R.drawable.number_five));
        numbersList.add(new Word("six", "temmokka", R.raw.number_six, R.drawable.number_six));
        numbersList.add(new Word("seven", "kenekaku",R.raw.number_seven, R.drawable.number_seven));
        numbersList.add(new Word("eight", "kawinta", R.raw.number_eight, R.drawable.number_eight));
        numbersList.add(new Word("nine", "wo’e", R.raw.number_nine, R.drawable.number_nine));
        numbersList.add(new Word("ten", "na’aacha", R.raw.number_ten, R.drawable.number_ten));
    }
    private void populateFamilyList(){
        familyList.add(new Word("father", "әpә", R.raw.family_father, R.drawable.family_father));
        familyList.add(new Word("mother", "әṭa", R.raw.family_mother, R.drawable.family_mother));
        familyList.add(new Word("son", "angsi", R.raw.family_son, R.drawable.family_son));
        familyList.add(new Word("daughter", "tune", R.raw.family_daughter, R.drawable.family_daughter));
        familyList.add(new Word("older brother", "taachi", R.raw.family_older_brother, R.drawable.family_older_brother));
        familyList.add(new Word("younger brother", "chalitti", R.raw.family_younger_brother, R.drawable.family_younger_brother));
        familyList.add(new Word("older sister", "teṭe", R.raw.family_older_sister, R.drawable.family_older_sister));
        familyList.add(new Word("younger sister", "kolliti", R.raw.family_younger_sister,R.drawable.family_younger_sister));
        familyList.add(new Word("grand mother", "ama", R.raw.family_grandmother, R.drawable.family_grandmother));
        familyList.add(new Word("grand father", "paapa", R.raw.family_grandfather, R.drawable.family_grandfather));
    }
    private void populateColorList(){
        colorList.add(new Word("red", "weṭeṭṭi", R.raw.color_red, R.drawable.color_red));
        colorList.add(new Word("green", "chokokki", R.raw.color_green, R.drawable.color_green));
        colorList.add(new Word("brown", "ṭakaakki", R.raw.color_brown, R.drawable.color_brown));
        colorList.add(new Word("gray", "ṭopoppi", R.raw.color_gray, R.drawable.color_gray));
        colorList.add(new Word("black", "kululli", R.raw.color_black, R.drawable.color_black));
        colorList.add(new Word("white", "kelelli", R.raw.color_white, R.drawable.color_white));
        colorList.add(new Word("dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow));
        colorList.add(new Word("mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));
    }
    private void populatePhrasesList(){
        phrasesList.add(new Word("Where are you going?", "minto wuksus", 0));
        phrasesList.add(new Word("What is your name?", "tinnә oyaase'nә", 0));
        phrasesList.add(new Word("My name is...", "oyaaset...", 0));
        phrasesList.add(new Word("How are you feeling?", "michәksәs?", 0));
        phrasesList.add(new Word("I’m feeling good.", "kuchi achit", 0));
        phrasesList.add(new Word("Are you coming?", "әәnәs'aa?", 0));
        phrasesList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", 0));
        phrasesList.add(new Word("I’m coming.", "әәnәm", 0));
        phrasesList.add(new Word("Let’s go.", "yoowutis", 0));
        phrasesList.add(new Word("Come here.", "әnni'nem", 0));
    }

    void stopPlayer(){
        if (player != null) {
            audioManager.abandonAudioFocus(MainActivity.this);
            player.stop();
            player.release();

            player = null;
        }

    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        Toast.makeText(this, "OVER HEEEREEEE!", Toast.LENGTH_SHORT).show();
        switch (focusChange){
            case AudioManager.AUDIOFOCUS_GAIN:
                player.start();
                Toast.makeText(this, "Started Gain!", Toast.LENGTH_SHORT).show();
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                stopPlayer();
                Toast.makeText(this, "LOSS", Toast.LENGTH_SHORT).show();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                Toast.makeText(this, "Loss transient", Toast.LENGTH_SHORT).show();
                stopPlayer();
                break;
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                Toast.makeText(this, "Gain Transient", Toast.LENGTH_SHORT).show();
                player.start();
                break;
            default:
                stopPlayer();
        }
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        stopPlayer();
    }

    @Override
    protected void onDestroy() {
        numbersList.clear();
        familyList.clear();
        colorList.clear();
        phrasesList.clear();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        stopPlayer();
        super.onStop();
    }
}