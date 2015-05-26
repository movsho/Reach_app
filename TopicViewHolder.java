package com.reach.reach_app_v10;

import android.app.Activity;
import android.graphics.AvoidXfermode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;


public class TopicViewHolder extends ArrayAdapter<Model> {

    private final List<Model> list;
    private final Activity context;

    public TopicViewHolder(Activity context, List<Model> list) {
        super(context, R.layout.topic_list_adapter, list);
        this.context = context;
        this.list = list;
    }
    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
        protected Button button;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.topic_list_adapter, null);
            final ViewHolder viewHolder = new ViewHolder();
            //viewHolder.button = (Button) view.findViewById(R.id.button_folow);
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            Model element = (Model) viewHolder.checkbox
                                    .getTag();
                            element.setSelected(buttonView.isChecked());

                        }
                    });
            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getName());
        holder.checkbox.setChecked(list.get(position).isSelected());
        return view;
    }

    public List<Model> getList ()
    {
        return list;
    }
}


