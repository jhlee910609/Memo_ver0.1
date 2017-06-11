package com.junhee.android.practicingormlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import static com.junhee.android.practicingormlite.DetailActivity.DOC_KEY;

/**
 * Created by JunHee on 2017. 6. 10..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    // public static List<Memo> del_list;

    List<Memo> memos;
    Context context;
    MemoDao dao;
    Memo memo;

    public RecyclerAdapter(List<Memo> memos, Context context) {
        dao = new MemoDao(context);
        this.memos = memos;
        this.context =context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        memo = memos.get(position);
        holder.setTitle(memo.getTitle());
        holder.setDate(memo.getDate().toString());
        holder.setMemo_id(memo.getId());

 /*      ===== [ 삭제용 체크박스 만들기 ] ====

        holder.checkBox.setChecked(memo.isChecked());
        holder.checkBox.setTag(memos.get(position));

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del_list = new ArrayList<Memo>();
                CheckBox cb = (CheckBox) v;
                Memo del_memo = (Memo) v.getTag();
                del_list.add(del_memo);

                del_memo.setChecked(cb.isChecked());
                memos.get(position).setChecked(cb.isChecked());
            }
        });

        */
    }

    public void setMemoList(List<Memo> memos){
        this.memos = memos;
    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        TextView title, date;
        private int memo_id;


        public int getMemo_id() {
            return memo_id;
        }

        public void setMemo_id(int memo_id) {
            this.memo_id = memo_id;
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setDate(String date) {
            this.date.setText(date);
        }

        public void setCheckBox(boolean isChecked) {
            this.checkBox.setChecked(isChecked);
        }

        public Holder(View itemView) {

            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            title = (TextView) itemView.findViewById(R.id.main_title);
            date = (TextView) itemView.findViewById(R.id.main_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO 완성하기
                    Intent intent =  new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra(DOC_KEY, memo.getId());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
