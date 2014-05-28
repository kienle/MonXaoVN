package com.kienle.monxaovn.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kienle.monxaovn.R;
import com.kienle.monxaovn.entity.MonXao;
import com.kienle.monxaovn.util.ImageLoader;

public class MonXaoAdapter extends BaseAdapter {
	
	private List<MonXao> mMonXaos;
	private ImageLoader mImageLoader;
	private LayoutInflater mInflater;

	public MonXaoAdapter(Context context, List<MonXao> monXaos) {
		this.mMonXaos = monXaos;
		this.mImageLoader = new ImageLoader(context); 
		this.mInflater = LayoutInflater.from(context);
	}
	
	public void setmMonXaos(List<MonXao> mMonXaos) {
		this.mMonXaos = mMonXaos;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mMonXaos.size();
	}

	@Override
	public Object getItem(int position) {
		return mMonXaos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.mon_xao_item, null);

            holder = new ViewHolder();
            holder.thumb = (ImageView) convertView.findViewById(R.id.thumb);
            holder.name = (TextView) convertView.findViewById(R.id.tvItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        mImageLoader.displayImage(mMonXaos.get(position).getThumb(), holder.thumb);
        holder.name.setText(mMonXaos.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
    	ImageView thumb;
        TextView name;
    }
}
