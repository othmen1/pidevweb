package tn.esprit.macdoloan.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.rmi.CORBA.Util;

import tn.esprit.macdoloan.entity.Branch;

import tn.esprit.macdoloan.entity.Governorate;

import tn.esprit.macdoloan.service.impl.BranchServiceImpl;

import com.jsf2leaf.model.Circle;
import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;
import com.jsf2leaf.model.Polyline;

@ManagedBean
@SessionScoped
public class BranchBean implements Serializable {
	
	private Branch cc;
	
	private int Id;

	private String street;

	private String postalCode;

	private String city;

	private String name;

	private Governorate governorate;
	
	@EJB
	BranchServiceImpl bs;

	private Map springfieldMap = new Map();
	
	public List<Branch> returnBranchs(){
		return bs.findAllBranchs();
	}
	
	public Map returnMap(){
		Map m = new Map();
		List<LatLong> coor = new ArrayList<LatLong>();
		coor.add(new LatLong("33.87388", "10.85965"));
		coor.add(new LatLong("35.82116", "10.63377"));
		coor.add(new LatLong("33.81119", "10.98795"));
		coor.add(new LatLong("36.84536", "10.18332"));
		coor.add(new LatLong("36.84712", "10.26854"));
		coor.add(new LatLong("37.27394", "9.87422"));
		coor.add(new LatLong("36.17212", "8.69959"));
		coor.add(new LatLong("33.88731", "10.10945"));
		
		
		
		Layer placesLayer = (new Layer()).setLabel("Places");
		int i= -1;
		for (Branch c : returnBranchs()) {
			i++;
			placesLayer.addMarker(new Marker(coor.get(i),("<b>"+c.getCity()+", "+c.getName()+" agency</b><br>Street: "+c.getStreet()+"<br>"+c.getPostalCode())));
		}
		


		m.setWidth("1270px").setHeight("500px").setCenter(new LatLong("33.98167","11.17466")).setZoom(6);
		m.addLayer(placesLayer);
		
		return m;
	}

	public String showBranch(Branch c){
		cc=c;
		String navigateTo = "/pages/client/claimDetails?faces-redirect=true";
		return navigateTo;
	}

	public Branch getCc() {
		return cc;
	}

	public void setCc(Branch cc) {
		this.cc = cc;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Governorate getGovernorate() {
		return governorate;
	}

	public void setGovernorate(Governorate governorate) {
		this.governorate = governorate;
	}

	public BranchServiceImpl getBs() {
		return bs;
	}

	public void setBs(BranchServiceImpl bs) {
		this.bs = bs;
	}

	public Map getSpringfieldMap() {
		return springfieldMap;
	}

	public void setSpringfieldMap(Map springfieldMap) {
		this.springfieldMap = springfieldMap;
	}



}
