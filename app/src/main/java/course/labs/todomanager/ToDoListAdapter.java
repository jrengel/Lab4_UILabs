package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;
	ViewHolder holder = null;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout itemLayout = (RelativeLayout)convertView;



		if (itemLayout == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			itemLayout = (RelativeLayout) inflater.inflate(R.layout.todo_item, parent, false);
			holder = new ViewHolder(itemLayout);
			itemLayout.setTag(holder);
		}else{
			holder = (ViewHolder)itemLayout.getTag();
		}


		// TODO - Get the current ToDoItem
		final ToDoItem toDoItem = (ToDoItem) getItem(position);


		// TODO - Inflate the View for this ToDoItem
		// from todo_item.xml
		//RelativeLayout itemLayout = null;


		// Fill in specific ToDoItem data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file

		// TODO - Display Title in TextView
		holder.title.setText(toDoItem.getTitle());

		// TODO - Set up Status CheckBox

		if (toDoItem.getStatus() == ToDoItem.Status.NOTDONE)
			holder.status.setChecked(false);
		else
			holder.status.setChecked(true);

		// TODO - Must also set up an OnCheckedChangeListener,
		// which is called when the user toggles the status checkbox

		holder.status.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				if (toDoItem.getStatus() == ToDoItem.Status.NOTDONE) {
					holder.status.setChecked(true);
					toDoItem.setStatus(ToDoItem.Status.DONE);
				} else {
					holder.status.setChecked(false);
					toDoItem.setStatus(ToDoItem.Status.NOTDONE);
				}

			}
		});

		// TODO - Display Priority in a TextView
		holder.priority.setText(toDoItem.getPriority().toString());


		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
		// time String

		holder.timeDate.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));


		// Return the View you just created
		return itemLayout;

	}

	class ViewHolder {
		TextView title;
		CheckBox status;
		TextView priority;
		TextView timeDate;

		ViewHolder(View v) {
			title = (TextView) v.findViewById(R.id.titleView);
			status = (CheckBox) v.findViewById(R.id.statusCheckBox);
			priority = (TextView) v.findViewById(R.id.priorityView);
			timeDate = (TextView) v.findViewById(R.id.dateView);
		}

	}

}
