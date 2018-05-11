package models;

import java.util.ArrayList;

public class Manager {

	private ArrayList<Img> imgList;
	
	public Manager() {
		imgList = new ArrayList<>();
	}
	
	public static Img createImg(String path){
		return new Img(path);
	}
	
	public void addImg(Img img){
		imgList.add(img);
	}

	public ArrayList<Img> getImgList() {
		return imgList;
	}
	
	public void clearArray(){
		imgList = new ArrayList<>();
	}
}