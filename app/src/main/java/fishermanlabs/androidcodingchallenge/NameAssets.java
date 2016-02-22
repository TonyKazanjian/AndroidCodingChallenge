package fishermanlabs.androidcodingchallenge;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * Created by tonyk_000 on 2/20/2016.
 */
public class NameAssets {

    private AssetManager mAssets;

    public NameAssets(Context context){
        mAssets = context.getAssets();
    }
    public AssetManager getAssets(){
        return mAssets;
    }
}
