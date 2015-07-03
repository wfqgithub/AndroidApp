package com.example.demoapp.Utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2015/7/3.
 */
public class ImageLoaderUtils {

        public static void loadImage(ImageView imageView,String url){
            ImageLoader.getInstance().displayImage(url,imageView);
        }
}
