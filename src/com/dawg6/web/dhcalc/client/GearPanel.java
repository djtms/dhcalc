/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import java.util.Map;
import java.util.TreeMap;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.client.ApplicationPanel.DialogBoxResultHandler;
import com.dawg6.gwt.common.util.DefaultCallback;
import com.dawg6.web.dhcalc.shared.calculator.Slot;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	private final Map<Slot, ItemHolder> items = new TreeMap<Slot, ItemHolder>();
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

		for (Slot slot : Slot.values()) if (!slot.isCube()) {
			final Slot thisSlot = slot;

			Label label = new Label(slot.name());
			label.addStyleName("boldText");
			table.setWidget(row, 0, label);

			Anchor status = new Anchor("unloaded");
			status.setTarget("_blank");
			status.setHref("javascript:void(0);");
			table.setWidget(row, 1, status);
			labels.put(slot, status);
			status.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					event.preventDefault();
					clickItem(thisSlot);

				}
			});

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
		
		setVisible(false);
	}

	protected void clickItem(Slot slot) {
		ItemHolder item = items.get(slot);

		if (item != null) {
			Window.open(
					"json?realm=US&item="
							+ URL.encodeQueryString(item.tooltip),
					"_blank", "");
		}
	}

	protected void clearSlot(Slot slot) {
		setItem(slot, null);
	}

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
			ItemHolder item = new ItemHolder(value);
			setItem(slot, item);
		}
	}

	protected void saveSlot(Slot slot) {
		ItemHolder item = items.get(slot);

		if (item != null) {
			
			item.getInfo(new DefaultCallback<ItemInformation>(){

				@Override
				protected void doOnSuccess(ItemInformation result) {
					saveItem(result.name, result);
				}});
			
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
			for (Slot slot : Slot.values()) if (!slot.isCube()) {
				ItemInformation item = hero.items.get(slot.getSlot());
				
				
				if (item != null)
					setItem(slot, new ItemHolder(item));
				else
					setItem(slot, null);
			}
		}

	}

	private void setItem(Slot slot, final ItemHolder item) {
		final Anchor label = labels.get(slot);

		String text = "unloaded";
		String url = "javascript:void(0);";

		if (item != null) {
			items.put(slot, item);
			url = "http://us.battle.net/d3/en/itemData/" + item.getTooltip();
			
			if (this.isVisible()) {
				item.getInfo(new DefaultCallback<ItemInformation>(){
	
					@Override
					protected void doOnSuccess(ItemInformation result) {
						label.setText(result.name);
					}
					
				});
			}
			
		} else {
			items.remove(slot);
			label.setText(text);
		}

		label.setHref(url);
	}

	public void updateLabels() {
		for (Slot s : Slot.values()) if (!s.isCube()) {
			final Anchor label = labels.get(s);
			ItemHolder item = items.get(s);
			
			if (item != null) {
				String url = "http://us.battle.net/d3/en/itemData/" + item.getTooltip();
				label.setHref(url);
	
				item.getInfo(new DefaultCallback<ItemInformation>(){
					
					@Override
					protected void doOnSuccess(ItemInformation result) {
						label.setText(result.name);
					}
					
				});
			} else {
				label.setHref("javascript:void(0)");
				label.setText("Empty");
			}
		}
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

	public Map<Slot, ItemHolder> getItems() {
		return new TreeMap<Slot, ItemHolder>(items);
	}

	public void populateFormData(Map<String, String> data) {
		Map<Slot, ItemHolder> items = getItems();

		data.clear();

		for (Slot s : Slot.values()) if (!s.isCube()) {
			
			ItemHolder item = items.get(s);

			if ((item != null) && (item.getTooltip() != null)) {
				data.put(s.getSlot(), item.getTooltip());
			}
		}
	}

	public void restoreData(Map<String, String> items) {
		for (Slot s : Slot.values()) if (!s.isCube()) {
			final Slot slot = s;
			String item = items.get(s.getSlot());

			if (item != null) {
				setItem(slot, new ItemHolder(item));
			} else {
				setItem(slot, null);
			}
		}
	}

	public void clearData() {
		
		for (Slot s : Slot.values())
			if (!s.isCube()) setItem(s, null);
	}
	
	public static class ItemHolder {
		private final String tooltip;
		private ItemInformation info;
		
		public ItemHolder(String tooltip) {
			this.tooltip = tooltip;
			this.info = null;
		}
		
		public ItemHolder(ItemInformation info) {
			this.tooltip = info.tooltipParams;
			this.info = info;
		}

		public String getTooltip() {
			return tooltip;
		}
		
		public void getInfo(AsyncCallback<ItemInformation> callback) {
			if (info == null) {
				
				Service.getInstance().getItem(Realm.US, tooltip,
						new DefaultCallback<ItemInformation>(callback) {

							@Override
							protected void doOnSuccess(ItemInformation result) {
								info = result;
							}
					
				});
			} else {
				callback.onSuccess(info);
			}
		}
	}
}
