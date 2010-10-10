package ru.kc.model.knowledge;

import ru.chapaj.util.UuidGenerator;
import ru.kc.model.HavingUuid;
import ru.kc.model.knowledge.role.Parent;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


/**
 * Элемент рута
 * @author jenua.dolganov
 *
 */
public abstract class Element implements Parent, HavingUuid {

	protected String uuid;
	protected Long createDate;
	@XStreamOmitField
	private Container parent;

	public Element() {
		super();
	}

	public Container getParent() {
		return parent;
	}

	public void setParent(Container parent) {
		this.parent = parent;
	}

	public String getUuid() {
		if(uuid == null) {
			uuid = UuidGenerator.simpleUuid();
		}
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!getClass().equals(obj.getClass())) return false;
		if(!getUuid().equals(((Element)obj).getUuid())) return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() +": " + getUuid();
	}

}