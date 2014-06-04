package com.semeureka.frame.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "T_FRAME_MENU")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "parent" })
public class Menu implements Serializable {
	@Id
	private Integer id;
	@Column(name = "MENU_NAME")
	private String name;
	@Column(name = "MENU_URL")
	private String url;
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Menu parent;
	@OneToMany(mappedBy = "parent")
	@OrderBy("id")
	private List<Menu> children;
	@Transient
	private String defaultUrl;
	@Transient
	private String path;
	@Transient
	private Integer level;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public String getDefaultUrl() {
		if (defaultUrl == null) {
			if (url != null) {
				defaultUrl = url;
			} else if (children != null) {
				for (Menu menu : children) {
					String durl = menu.getDefaultUrl();
					if (durl != null) {
						defaultUrl = durl;
						break;
					}
				}
			}
		}
		return defaultUrl;
	}

	public String getPath() {
		if (path == null && id != null) {
			path = id.toString();
			Menu p = parent;
			while (p != null) {
				path = p.getId() + "/" + path;
				p = p.getParent();
			}
		}
		return path;
	}

	public Integer getLevel() {
		if (level == null && id != null) {
			level = 0;
			Menu p = parent;
			while (p != null) {
				level++;
				p = p.getParent();
			}
		}
		return level;
	}

	public List<Menu> getChildren(List<Menu> menus) {
		if (menus == null) {
			menus = new LinkedList<Menu>();
		}
		menus.add(this);
		if (children != null) {
			for (Menu menu : children) {
				menu.getChildren(menus);
			}
		}
		return menus;
	}
}
