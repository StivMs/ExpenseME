package da345af1.ai2530.mah.se.p1_stivan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {
    List<Income> incomeList;
    Context context;
    int pos;
    String date;

    public IncomeAdapter(List<Income> incomesList, Context context) {
        this.incomeList = incomesList;
        this.context = context;
    }

    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_income_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IncomeAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.category.setText(incomeList.get(position).getCategory());
        viewHolder.type.setText(incomeList.get(position).getType());
        viewHolder.amount.setText(incomeList.get(position).getAmount().toString());
        viewHolder.date.setText(String.valueOf(incomeList.get(position).getDate()));
        pos = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = incomeList.get(pos).getCategory();
                String type = incomeList.get(pos).getType();
                String amount = incomeList.get(pos).getAmount().toString();
                String date = String.valueOf(incomeList.get(pos).getDate());
                Intent intent = new Intent(context, IncomeMoreInfoActivity.class);
                intent.putExtra("incCategory", category);
                intent.putExtra("incType", type);
                intent.putExtra("incAmount", amount);
                intent.putExtra("incDate", date);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public TextView type;
        public TextView amount;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.tvIncomeCategory);
            type = itemView.findViewById(R.id.tvIncomeType);
            amount = itemView.findViewById(R.id.tvIncomeAmount);
            date = itemView.findViewById(R.id.tvIncomeDate);
        }
    }

}
