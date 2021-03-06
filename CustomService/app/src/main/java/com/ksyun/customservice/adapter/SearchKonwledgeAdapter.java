package com.ksyun.customservice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ksyun.customservice.KnowledgeDetailActivity;
import com.ksyun.customservice.R;
import com.ksyun.customservice.object.KnowledgeAllObject.KonwledgeObject;

import java.util.List;


public class SearchKonwledgeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<KonwledgeObject> questionList;
    private Context context;

    public SearchKonwledgeAdapter(List<KonwledgeObject> questionList, Context context) {
        inflater = LayoutInflater.from(context);
        this.questionList=questionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return questionList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return questionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void changeLibraryList(List<KonwledgeObject> questionList) {
        this.questionList=questionList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.knowledge_library_item, null);
            holder.konwledge_layout = (RelativeLayout) convertView
                    .findViewById(R.id.konwledge_layout);

            holder.share_app_text = (TextView) convertView
                    .findViewById(R.id.konwledge_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (questionList.size() > 0) {
            final KonwledgeObject object = questionList.get(position);
//            Log.d("gaolei", "position)-----------------------" + position);
           final  String question=object.getQuestion();
            final String answer=object.getAnswer();
            holder.share_app_text.setText(question);
//            Log.d("gaolei", "object.getQuestion()-----------------------" + object.getQuestion());
//            Log.d("gaolei", "object.getAnswer()-----------------------" + object.getAnswer());

            final Intent intent = new Intent(context, KnowledgeDetailActivity.class);
            holder.konwledge_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("question", question);
                    intent.putExtra("answer", answer);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;

    }

    class ViewHolder {
        TextView share_app_text;
        RelativeLayout konwledge_layout;
    }
}