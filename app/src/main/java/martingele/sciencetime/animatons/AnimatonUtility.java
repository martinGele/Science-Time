package martingele.sciencetime.animatons;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by martin on 31.03.2016.
 */
public class AnimatonUtility {


    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {


        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 200 : -200, 0);
        animatorTranslateY.setDuration(400);


        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -50, 50, -30, 30, -20, 20, -5, 5, 0);
        animatorTranslateX.setDuration(1000);

        // animatorSet.playTogether(animatorTranslateX, animatorTranslateY);

        animatorSet.playTogether(animatorTranslateY);
        animatorSet.start();

    }
}