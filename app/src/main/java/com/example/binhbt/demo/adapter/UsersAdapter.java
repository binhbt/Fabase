package com.example.binhbt.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binhbt.demo.R;
import com.example.binhbt.demo.model.User;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

  public interface OnItemClickListener {
    void onUserItemClicked(User userModel);
  }

  private List<User> usersCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  public UsersAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.usersCollection = Collections.emptyList();
  }

  @Override
  public int getItemCount() {
    return (this.usersCollection != null) ? this.usersCollection.size() : 0;
  }

  @Override
  public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_user, parent, false);
    return new UserViewHolder(view);
  }

  @Override
  public void onBindViewHolder(UserViewHolder holder, final int position) {
    final User userModel = this.usersCollection.get(position);
    Picasso.with(holder.avatar.getContext()).load(userModel.getCoverUrl()).into(holder.avatar);
    holder.textViewTitle.setText(userModel.getFullName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (UsersAdapter.this.onItemClickListener != null) {
          UsersAdapter.this.onItemClickListener.onUserItemClicked(userModel);
        }
      }
    });
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  public void setUsersCollection(Collection<User> usersCollection) {
    this.validateUsersCollection(usersCollection);
    this.usersCollection = (List<User>) usersCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateUsersCollection(Collection<User> usersCollection) {
    if (usersCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class UserViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView textViewTitle;
    @BindView(R.id.avatar)
    ImageView avatar;
    public UserViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
