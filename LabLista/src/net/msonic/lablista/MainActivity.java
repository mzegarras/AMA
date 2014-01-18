package net.msonic.lablista;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ClienteTO c1 = new ClienteTO();
		c1.Nombres="A";
		
		ClienteTO c2 = new ClienteTO();
		c2.Nombres="B";
		
		ClienteTO c3 = new ClienteTO();
		c3.Nombres="B";
		
		List<ClienteTO> clientes = new ArrayList<ClienteTO>();
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		
		ClienteArrayAdapter adapter = new ClienteArrayAdapter(this, clientes);
		setListAdapter(adapter);
		
	}

	public static class ClienteArrayAdapter extends ArrayAdapter<ClienteTO> {

		public ClienteArrayAdapter(Context context, List<ClienteTO> objects) {
			super(context, R.layout.listsimple03_content, objects);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.listado = objects;

		}

		public final Context context;
		public final List<ClienteTO> listado;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			View view = null;
			if (convertView == null) {

				LayoutInflater inflator = (LayoutInflater) context
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				view = inflator.inflate(R.layout.listsimple03_content, null);

				final ViewHolder holder = new ViewHolder();

				holder.textView = (TextView) view.findViewById(R.id.txtNombres);
				holder.button = (Button) view.findViewById(R.id.btnEliminar);

				holder.textView.setTag(this.listado.get(position));

				view.setTag(holder);

			} else {
				view = convertView;
				((ViewHolder) view.getTag()).textView.setTag(this.listado.get(position));
			}
			
			
			final ViewHolder holder = (ViewHolder) view.getTag();
			ClienteTO cliente = listado.get(position);
			
			
			holder.textView.setText(cliente.Nombres);
			
			
			holder.button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ClienteTO cliente = (ClienteTO)holder.textView.getTag();
					listado.remove(cliente);
					notifyDataSetInvalidated();

				}
			});

			return view;
		}

		static class ViewHolder {
			TextView textView;
			Button button;

		}
	}

}
