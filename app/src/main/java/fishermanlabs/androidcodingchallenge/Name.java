package fishermanlabs.androidcodingchallenge;

/**
 * Created by tonyk_000 on 2/20/2016.
 */
public class Name {
    String mFirstName;
    String mLastName;

    public Name(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    public Name(){

    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }
}
