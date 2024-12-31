package com.semesta.icnema_uts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private List<Ticket> ticketList;
    private FragmentActivity activity;

    public TicketAdapter(List<Ticket> ticketList, FragmentActivity activity) {
        this.ticketList = ticketList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.title.setText("Title: " + ticket.getTitle());
        holder.overview.setText("Overview: " + ticket.getOverview());
        holder.releaseDate.setText("Release Date: " + ticket.getReleaseDate());
        holder.time.setText("Time: " + ticket.getTime()); // Bind time field
        holder.seats.setText("Seats: " + ticket.getSeats());
        holder.price.setText("Price: Rp. " + ticket.getPrice());
        Glide.with(holder.itemView.getContext()).load(ticket.getImageUrl()).into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            String bookingInfo = "Title: " + ticket.getTitle() + "\nOverview: " + ticket.getOverview() +
                    "\nRelease Date: " + ticket.getReleaseDate() + "\nTime: " + ticket.getTime() + // Add time to booking info
                    "\nSeats: " + ticket.getSeats() + "\nPrice: Rp. " + ticket.getPrice();
            QRCodeDialogFragment qrCodeDialog = QRCodeDialogFragment.newInstance(bookingInfo);
            qrCodeDialog.show(activity.getSupportFragmentManager(), "QRCodeDialog");
        });
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView title, overview, releaseDate, time, seats, price; // Add time TextView
        ImageView image;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ticket_title);
            overview = itemView.findViewById(R.id.ticket_overview);
            releaseDate = itemView.findViewById(R.id.ticket_release_date);
            time = itemView.findViewById(R.id.ticket_time); // Initialize time TextView
            seats = itemView.findViewById(R.id.ticket_seats);
            price = itemView.findViewById(R.id.ticket_price);
            image = itemView.findViewById(R.id.ticket_image);
        }
    }
}