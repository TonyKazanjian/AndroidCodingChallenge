package fishermanlabs.androidcodingchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tonyk_000 on 2/20/2016.
 */
public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameHolder> {

    List<Name> mNames;

    NameAdapter(List<Name> names) {
        mNames = names;
    }

    @Override
    public NameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NameHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_name, parent, false));
    }

    @Override
    public void onBindViewHolder(NameHolder holder, int position) {
        Name name = mNames.get(position);
        capitalizeName(name);
        holder.mNameView.setText(name.getLastName() + ", " + name.getFirstName());

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public void addName(Name name){
        mNames.add(name);
        notifyItemInserted(mNames.size()-1);
    }

    private Name capitalizeName(Name name) {
        String firstName = name.getFirstName();
        String lastName = name.getLastName();

        name.setFirstName(String.valueOf(firstName.charAt(0)).toUpperCase() + firstName.subSequence(1, firstName.length()));
        name.setLastName(String.valueOf(lastName.charAt(0)).toUpperCase() + lastName.subSequence(1, lastName.length()));

        return name;
    }

    protected static class NameHolder extends RecyclerView.ViewHolder{

        private TextView mNameView;

        public NameHolder(View itemView) {
            super(itemView);
            mNameView = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
