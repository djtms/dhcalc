package com.dawg6.web.sentry.client;

import java.util.Map;
import java.util.TreeMap;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.client.ApplicationPanel.DialogBoxResultHandler;
import com.dawg6.gwt.common.util.DefaultCallback;
import com.dawg6.web.sentry.shared.calculator.Slot;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GearPanel extends Composite {

	private final Map<Slot, Anchor> labels = new TreeMap<Slot, Anchor>();
	private final Map<Slot, ItemInformation> items = new TreeMap<Slot, ItemInformation>();
	private final String SAVED_ITEMS = "SAVED_ITEMS";
	private final ListBox savedItems;

	@SuppressWarnings("unchecked")
	public GearPanel() {

		HorizontalPanel panel = new HorizontalPanel();
		initWidget(panel);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		panel.add(verticalPanel_1);

		CaptionPanel captionPanel = new CaptionPanel("Current Items");
		verticalPanel_1.add(captionPanel);

		FlexTable table = new FlexTable();
		captionPanel.add(table);
		
		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Saved Items");
		panel.add(cptnpnlNewPanel);

		VerticalPanel verticalPanel = new VerticalPanel();
		cptnpnlNewPanel.setContentWidget(verticalPanel);
		verticalPanel.setSize("5cm", "3cm");

		savedItems = new ListBox();
		verticalPanel.add(savedItems);
		savedItems.setVisibleItemCount(20);
		savedItems.setWidth("100%");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		verticalPanel.add(horizontalPanel);

		Button btnNewButton = new Button("New button");
		btnNewButton.setText("Rename...");
		horizontalPanel.add(btnNewButton);

		btnNewButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				renameItem();
			}
		});

		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("Delete");
		horizontalPanel.add(btnNewButton_1);

		btnNewButton_1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteItem();
			}
		});

		int row = 0;

		for (Slot slot : Slot.values()) {
			final Slot thisSlot = slot;

			Label label = new Label(slot.name());
			label.addStyleName("boldText");
			table.setWidget(row, 0, label);

			Anchor status = new Anchor("unloaded");
			status.setTarget("_blank");
			status.setHref("javascript: return false;");
			table.setWidget(row, 1, status);
			labels.put(slot, status);
			status.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					event.preventDefault();
					clickItem(thisSlot);
					
				}});
			
			Button saveButton = new Button("save");
			table.setWidget(row, 2, saveButton);
			saveButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					saveSlot(thisSlot);
				}
			});

			Button replaceButton = new Button("replace");
			table.setWidget(row, 3, replaceButton);
			replaceButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					replaceSlot(thisSlot);
				}
			});

			Button clearButton = new Button("clear");
			table.setWidget(row, 4, clearButton);
			clearButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					clearSlot(thisSlot);
				}
			});

			row++;
		}

		Storage storage = getStorage();

		if (storage != null) {
			String data = storage.getItem(SAVED_ITEMS);
			JsoArray<Entry> list = null;

			if (data != null) {
				list = JsonUtils.safeEval(data);
			} else {
				list = (JsoArray<Entry>) JavaScriptObject.createArray();
			}

			for (int i = 0; i < list.length(); i++) {
				Entry e = list.get(i);
				savedItems.addItem(e.getName(), e.getItem());
			}

		}
	}

	protected void clickItem(Slot slot) {
		ItemInformation item = items.get(slot);
		
		if (item != null) {
			Window.open("json?realm=US&item=" + URL.encodeQueryString(item.tooltipParams), "_blank", "");
		}
	}

	protected void clearSlot(Slot slot) {
		setItem(slot, null);
	}
	
	@SuppressWarnings("unchecked")
	protected void deleteItem() {
		int i = savedItems.getSelectedIndex();

		if (i >= 0) {
			String name = savedItems.getItemText(i);
			String item = savedItems.getValue(i);

			JsoArray<Entry> list = removeItem(getSavedItems(), name, item);

			saveItems(list);

			savedItems.removeItem(i);
			savedItems.setSelectedIndex(i - 1);
		}

	}

	private JsoArray<Entry> removeItem(JsoArray<Entry> list, String name,
			String item) {

		@SuppressWarnings("unchecked")
		JsoArray<Entry> copy = (JsoArray<Entry>) JavaScriptObject.createArray();

		for (int n = 0; n < list.length(); n++) {
			Entry e = list.get(n);

			if (!name.equals(e.getName()) || !item.equals(e.getItem()))
				copy.push(e);
		}

		return copy;
	}

	protected void renameItem() {
		final int i = savedItems.getSelectedIndex();

		if (i >= 0) {
			final String name = savedItems.getItemText(i);
			final String item = savedItems.getValue(i);

			final TextBox field = new TextBox();
			field.setText(name);

			ApplicationPanel.showDialogBox("Rename Item", field,
					ApplicationPanel.OK | ApplicationPanel.CANCEL,
					new DialogBoxResultHandler() {

						@Override
						public void dialogBoxResult(int result) {

							if (result == ApplicationPanel.OK) {
								JsoArray<Entry> list = removeItem(
										getSavedItems(), name, item);
								Entry e = (Entry) JavaScriptObject
										.createObject();
								e.setName(field.getText());
								e.setItem(item);
								list.push(e);
								saveItems(list);

								savedItems.removeItem(i);
								savedItems.insertItem(e.getName(), item, i);
								savedItems.setSelectedIndex(i);
							}

						}
					});
		}

	}

	protected void replaceSlot(final Slot slot) {
		int i = savedItems.getSelectedIndex();

		if (i >= 0) {
			String value = savedItems.getValue(i);

			Service.getInstance().getItem(Realm.US, value,
					new DefaultCallback<ItemInformation>() {

						@Override
						protected void doOnSuccess(final ItemInformation item) {

							if ((item == null) || (item.code != null)) {
								ApplicationPanel
										.showErrorDialog("Error loading item");
								return;
							}

							setItem(slot, item);
						}
					});
		}

	}

	protected void saveSlot(Slot slot) {
		ItemInformation item = items.get(slot);

		if (item != null) {
			saveItem(item.name, item);
		}
	}

	@SuppressWarnings("unchecked")
	private JsoArray<Entry> getSavedItems() {
		Storage storage = getStorage();

		if (storage != null) {
			String data = storage.getItem(SAVED_ITEMS);
			JsoArray<Entry> list = null;

			if (data != null) {
				list = JsonUtils.safeEval(data);
			} else {
				list = (JsoArray<Entry>) JavaScriptObject.createArray();
			}

			return list;

		} else {
			return (JsoArray<Entry>) JavaScriptObject.createArray();
		}
	}

	private void saveItems(JsoArray<Entry> list) {
		Storage storage = getStorage();

		if (storage != null) {
			storage.setItem(SAVED_ITEMS, list.stringify());
		}
	}

	private void saveItem(String name, ItemInformation item) {

		Storage storage = getStorage();

		if (storage != null) {

			Entry entry = (Entry) JavaScriptObject.createObject();
			entry.setName(name);
			entry.setItem(item.tooltipParams);

			String data = storage.getItem(SAVED_ITEMS);
			JsoArray<Entry> list = getSavedItems();

			list.push(entry);
			saveItems(list);

			savedItems.addItem(name, item.tooltipParams);
			int i = savedItems.getItemCount();
			savedItems.setSelectedIndex(i - 1);
		}

	}

	public void setHero(HeroProfile hero) {

		if ((hero != null) && (hero.items != null)) {
			for (Slot slot : Slot.values()) {
				ItemInformation item = hero.items.get(slot.getSlot());
				setItem(slot, item);
			}
		}

	}

	private void setItem(Slot slot, ItemInformation item) {
		Anchor label = labels.get(slot);

		String text = "unloaded";
		String url = "javascript: return false;";

		if (item != null) {
			text = item.name;
			items.put(slot, item);
			url = "http://us.battle.net/d3/en/itemData/" + item.tooltipParams;
		} else {
			items.remove(slot);
		}

		label.setText(text);
		label.setHref(url);
	}

	private static class Entry extends JavaScriptObject {

		protected Entry() {
		}

		public final native String getName() /*-{ return this.name; }-*/;

		public final native String getItem() /*-{ return this.item; }-*/;

		public final native void setName(String iName) /*-{ this.name = iName; }-*/;

		public final native void setItem(String iItem) /*-{ this.item = iItem; }-*/;
	}

	private static class JsoArray<T extends JavaScriptObject> extends
			JavaScriptObject {

		protected JsoArray() {
		}

		public final native int length() /*-{ return this.length; }-*/;

		public final native T get(int i) /*-{ return this[i]; }-*/;

		public final native void push(T item) /*-{ this.push(item); }-*/;

		public final native String stringify() /*-{ return JSON.stringify(this); }-*/;
	}

	private Storage getStorage() {
		Storage s = Storage.getLocalStorageIfSupported();

		if (s == null) {
			ApplicationPanel
					.showErrorDialog("Local Storage not supported by your browser");
		}

		return s;
	}

	public Map<Slot, ItemInformation> getItems() {
		return new TreeMap<Slot, ItemInformation>(items);
	}
}
