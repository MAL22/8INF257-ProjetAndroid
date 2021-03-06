package uqac8inf257.wikipersona.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import uqac8inf257.wikipersona.R;
import uqac8inf257.wikipersona.data.Shadow;

/**
 * Created by mimil on 2018-04-11.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private List<Shadow> mShadows;
    private Context mContext;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mRealName;
        public TextView mFakeName;

        public ViewHolder(final View itemView) {
            super(itemView);
            mRealName = itemView.findViewById(R.id.elementRealName);
            mFakeName = itemView.findViewById(R.id.elementFakeName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }


    public SearchListAdapter(Context context, List<Shadow> shadows) {
        mShadows = shadows;
        mContext = context;
    }

    @Override
    public SearchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View searchListView = inflater.inflate(R.layout.search_element, parent, false);

        ViewHolder viewHolder = new ViewHolder(searchListView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchListAdapter.ViewHolder viewHolder, int position) {
        Shadow shadow = mShadows.get(position);

        TextView textView = viewHolder.mRealName;
        textView.setText(shadow.getRealName());

        textView = viewHolder.mFakeName;
        textView.setText(shadow.getFakeName());


    }

    @Override
    public int getItemCount() {
        return mShadows.size();
    }

    private Context getContext() {
        return mContext;
    }


}
