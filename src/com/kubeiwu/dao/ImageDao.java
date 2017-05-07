package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.ADInfo;
import com.kubeiwu.bean.Image;

public class ImageDao implements IImage, Dao {

	@Override
	public List<Image> qureyAllImages() {
		List<Image> adInfos = new ArrayList<Image>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			IImage adInfo = sqlSession.getMapper(IImage.class);
			adInfos = adInfo.qureyAllImages();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return adInfos;
	}

	@Override
	public void insertOne(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertBatch(List<Image> images) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertReplace(List<Image> images) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countByGroup(int groupId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByGid(int gid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateLocalImage(Image image) {
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			IImage adInfo = sqlSession.getMapper(IImage.class);
			adInfo.updateLocalImage(image);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}
	@Override
	public void updatePixel(Image image) {
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//
			
			IImage adInfo = sqlSession.getMapper(IImage.class);
			adInfo.updatePixel(image);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		
	}

	@Override
	public Image qureyImageByUrl(String imageurl) {
		 Image  adInfos =null;
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			IImage adInfo = sqlSession.getMapper(IImage.class);
			adInfos = adInfo.qureyImageByUrl(imageurl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return adInfos;
	}

}
