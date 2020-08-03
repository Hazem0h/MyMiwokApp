# MyMiwokApp
## Disclaimer: 
  I do not own any of the media files or the images used in this app. Ownership belongs to Udacity
## Description:
This is my implementation of the Miwok app, as taught in the Android Basics Nanodegre at Udacity <br>

## A few notes:
1. I used a Recycler View instead of a ListView. ListViews are now deprecated
2. Instead of creating a Fragment class for each category, I created a single Fragment class, from which differnet fragment objects can be instantiated, depending on the current position of the ViewPager
3.  The main Activity (MyMainActivity) is the controller of mediaPlayback. Only one object of MediaPlayer exists at a time. So, no two tracks can play at the same time. Also Audiofocus is handled in MyMainAcitivity
