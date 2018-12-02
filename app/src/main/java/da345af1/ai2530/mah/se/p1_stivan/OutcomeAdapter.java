package da345af1.ai2530.mah.se.p1_stivan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OutcomeAdapter extends RecyclerView.Adapter<OutcomeAdapter.ViewHolder> {

    List<Outcomes> outcomesList;
    Context context;
    int pos;

    public OutcomeAdapter(List<Outcomes> outcomesList, Context context) {
        this.outcomesList = outcomesList;
        this.context = context;
    }

    @NonNull
    @Override
    public OutcomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutcomeAdapter.ViewHolder viewHolder, int position) {
        pos = position;
        viewHolder.category.setText(outcomesList.get(position).getCategory());
        viewHolder.type.setText(outcomesList.get(position).getType());
        viewHolder.amount.setText(outcomesList.get(position).getAmount().toString());
        viewHolder.date.setText(String.valueOf(outcomesList.get(position).getDate()));
        viewHolder.image.setImageResource(outcomesList.get(position).getImage());
        viewHolder.itemView.setOnClickListener(new MoreInfoListener());
    }

    @Override
    public int getItemCount() {
        return outcomesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public TextView type;
        public TextView amount;
        public TextView date;
        public ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.tvCategory);
            type = itemView.findViewById(R.id.tvIncomeType);
            amount = itemView.findViewById(R.id.tvAmount);
            date = itemView.findViewById(R.id.tvDate);
            image = itemView.findViewById(R.id.imIcon);

        }
    }

    private class MoreInfoListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String category = outcomesList.get(pos).getCategory();
            String type = outcomesList.get(pos).getType();
            String amount = outcomesList.get(pos).getAmount().toString();
            String date = outcomesList.get(pos).getDate().toString();
            int image = outcomesList.get(pos).getImage();
            Intent intent = new Intent(view.getContext(), OutcomeMoreInfoActivity.class);
            intent.putExtra("outCategory", category);
            intent.putExtra("outType", type);
            intent.putExtra("outAmount", amount);
            intent.putExtra("outDate", date);
            intent.putExtra("outIMG", image);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
        }
    }
}
