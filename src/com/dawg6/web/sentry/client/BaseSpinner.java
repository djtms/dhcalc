package com.dawg6.web.sentry.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ValueBox;

public abstract class BaseSpinner<T> extends Composite {

	protected final ValueBox<T> box;
	protected final Button up;
	protected final Button down;
	protected T min;
	protected T max;

	/**
	 * @wbp.parser.constructor
	 */
	protected BaseSpinner(ValueBox<T> box, String text, T min, T max) {
		this.box = box;
		this.min = min;
		this.max = max;

		box.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				DomEvent.fireNativeEvent(Document.get().createChangeEvent(), BaseSpinner.this.box);
			}});
		
		box.addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
				normalize();
			}
		});
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("spinner");
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		initWidget(horizontalPanel);
		
		down = new Button();
		down.setText("-");
		down.setStyleName("spinnerDownButton");
		horizontalPanel.add(down);
		
		down.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				increment(-1);
			}});
		
		box.setStyleName("spinnerBox");
		box.setText(text);
		horizontalPanel.add(box);
		
		box.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				BaseSpinner.this.box.selectAll();
			}});
		
		up = new Button();
		up.setText("+");
		up.setStyleName("spinnerUpButton");
		horizontalPanel.add(up);
		
		up.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				increment(1);
			}});
	}
	
	protected void normalize() {
		setValue(getValue());
	}

	protected abstract void increment(int step);

	protected abstract T bound(T value);
	
	protected abstract T parse(String text);
	
	public void setValue(T value) {
		T a = getValue();
		T b = bound(value);
		
		if (!isEqual(a, b)) {
			this.box.setValue(b);
			DomEvent.fireNativeEvent(Document.get().createChangeEvent(), box);
		}
	}
	
	public T getValue() {
		return this.box.getValue();
	}
	
	public void setVisibleLength(int w) {
		box.setVisibleLength(w);
	}
	
	public String getText() {
		return box.getText();
	}
	
	public void setText(String text) {
		setValue(parse(text));
	}
	
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return box.addChangeHandler(handler);
	}

	public T getMin() {
		return min;
	}

	public void setMin(T min) {
		this.min = min;
		normalize();
	}

	public T getMax() {
		return max;
	}

	public void setMax(T max) {
		this.max = max;
		normalize();
	}

	@Override
	public void setTitle(String title) {
		box.setTitle(title);
	}
	
	@Override
	public String getTitle() {
		return box.getTitle();
	}
	
	protected abstract boolean isEqual(T a, T b);
}
