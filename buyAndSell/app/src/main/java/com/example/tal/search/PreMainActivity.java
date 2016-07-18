package com.example.tal.search;
//This activity is shown when the user open the app.
//Class imports.
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

//Opening PreMainActivity activity.
public class PreMainActivity extends Activity
{
    //Class variables.
    public final long DURATION = 5000;//Animation duration for the activity.

    public TextView and;
    public ImageView img;

    //Method that executed when the activity create.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pre_main);
        and = (TextView)findViewById(R.id.and);
        img = (ImageView)findViewById(R.id.startImg);
        final View view = findViewById(R.id.main);

        //Here we do animation to the activity that it will fade out.
        Animation animation = AnimationUtils.loadAnimation(PreMainActivity.this,android.R.anim.fade_out);
        animation.setDuration(DURATION);
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                and.setVisibility(TextView.INVISIBLE);
                img.setVisibility(ImageView.INVISIBLE);
                view.setBackgroundColor(Color.WHITE);
                Intent mainIntent = new Intent(PreMainActivity.this,MainActivity.class);
                PreMainActivity.this.startActivity(mainIntent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        view.startAnimation(animation);
    }
}
