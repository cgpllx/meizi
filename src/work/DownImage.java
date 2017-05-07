package work;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.eclipse.jdt.internal.compiler.ast.ContinueStatement;

import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.Image;
import com.kubeiwu.bean.RequestListPara;
import com.kubeiwu.dao.GroupImageInfoDao;
import com.kubeiwu.dao.ImageDao;

public class DownImage {
	public static void main(String[] args) {
		GroupImageInfoDao groupImageInfoDao = new GroupImageInfoDao();
		// groupImageInfoDao.up
		ImageDao imagedao = new ImageDao();
	 
		List<GroupImageInfo> list = groupImageInfoDao.queryGroupImageInfoListByWhere("");
		for (GroupImageInfo groupImageInfo : list) {
			System.out.println("groupImageInfo=" + groupImageInfo.getTitle());
//			System.out.println("groupImageInfo=" + groupImageInfo.getImages());

			String imageurl = groupImageInfo.getCoverimage();
			if (imageurl == null || imageurl.equals("")) {
				continue;
			}
			Image image = imagedao.qureyImageByUrl(imageurl);
			if (image != null) {
				String localpic = image.getLocalpic();
				if (localpic == null || localpic.equals("")){
					continue;
				}else{
					groupImageInfo.setLocalcoverimage(localpic);
					groupImageInfoDao.updateLocalcoverimage(groupImageInfo);
				}
			}

//			try {
////				Thread.sleep(10000000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	public static void main2(String[] args) {
		GroupImageInfoDao groupImageInfoDao = new GroupImageInfoDao();
		// groupImageInfoDao.up
		ImageDao imagedao = new ImageDao();
		RequestListPara parameter = new RequestListPara();
		parameter.setDbIndex(1);
		parameter.setCategory_code(310);
		parameter.setPageCount(10);
		List<GroupImageInfo> list = groupImageInfoDao.queryGroupImageInfoList(parameter);
		for (GroupImageInfo groupImageInfo : list) {
			System.out.println("groupImageInfo=" + groupImageInfo.getTitle());
			System.out.println("groupImageInfo=" + groupImageInfo.getImages());

			List<Image> images = groupImageInfoDao.queryGroupImageInfoById(groupImageInfo.getCategory_code()).getImages();
			String id = groupImageInfo.getId() + "";
			try {
				for (int i = 0; i < images.size(); i++) {
					Image image = images.get(i);
					String imageurl = image.getImageurl();
					FileUtils.copyURLToFile(new URL(imageurl), new File("/Users/chenguoping/androidDev/images" + id, id + "_" + i));
					imagedao.updateLocalImage(image);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main1(String[] args) throws InterruptedException {
		// LinkedBlockingDeque<Runnable> queue = new
		// LinkedBlockingDeque<Runnable>();
		ScheduledExecutorService scheduledThreadPool1 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool2 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool3 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool4 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool5 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool6 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool7 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool8 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool9 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool11 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool12 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool13 = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService scheduledThreadPool14 = Executors.newScheduledThreadPool(1);
		final LinkedBlockingDeque<ScheduledExecutorService> queueRun = new LinkedBlockingDeque<ScheduledExecutorService>();
		queueRun.add(scheduledThreadPool1);
		queueRun.add(scheduledThreadPool2);
		queueRun.add(scheduledThreadPool3);
		queueRun.add(scheduledThreadPool4);
		queueRun.add(scheduledThreadPool5);
		queueRun.add(scheduledThreadPool6);
		queueRun.add(scheduledThreadPool7);
		queueRun.add(scheduledThreadPool8);
		queueRun.add(scheduledThreadPool9);
		queueRun.add(scheduledThreadPool11);
		queueRun.add(scheduledThreadPool12);
		queueRun.add(scheduledThreadPool13);
		queueRun.add(scheduledThreadPool14);

		final GroupImageInfoDao groupImageInfoDao = new GroupImageInfoDao();
		final ImageDao imagedao = new ImageDao();
		List<Image> list = imagedao.qureyAllImages();
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

		for (Image image : list) {
			final ScheduledExecutorService scheduledThreadPoolzzz = queueRun.take();
			final Image imageold = image;
			Runnable r = new Runnable() {
				//
				@Override
				public void run() {
					System.out.println("groupImageInfo url=" + imageold.getImageurl());
					String imageurl = imageold.getImageurl();
					int id = imageold.getGroupimageinfo_id();
					// String uuids = UUID.randomUUID().toString();

					String uuids = imageold.getId() + "";

					try {
						GroupImageInfo groupImageInfo = groupImageInfoDao.queryGroupImageInfoById(imageold.getGroupimageinfo_id());
						int categoryCode = groupImageInfo.getCategory_code();
						System.out.println(categoryCode);
						if (categoryCode != 320) {
							return;
						}
						FileUtils.copyURLToFile(new URL(imageurl), new File("/Users/chenguoping/androidDev/images/" + categoryCode + "/" + id, id + "_" + uuids + ".jpg"));
						String relativePath = "/" + categoryCode + "/" + id + "/" + id + "_" + uuids + ".jpg";
						imageold.setLocalpic(relativePath);
						imagedao.updateLocalImage(imageold);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						queueRun.add(scheduledThreadPoolzzz);
					}

				}
			};

			System.out.println("gcccccccccccccc=");
			scheduledThreadPoolzzz.execute(r);

		}
		while (true) {
			try {
				ScheduledExecutorService scheduledThreadPoolzzz = queueRun.take();
				System.out.println("gcccccccccccccc=");
				// scheduledThreadPoolzzz.execute(queue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
