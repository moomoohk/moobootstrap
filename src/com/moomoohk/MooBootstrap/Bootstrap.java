package com.moomoohk.MooBootstrap;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.moomoohk.Mootilities.FileUtils.FileUtils;
import com.moomoohk.Mootilities.MooDownloader.gui.DownloaderShell;
import com.moomoohk.Mootilities.OSUtils.OSUtils;
import com.moomoohk.Mootilities.Swing.FrameDragger;

/**
 * Bootstrap utility meant to aid in update checking and updating of files.
 * 
 * @author Meshulam Silk (moomoohk@ymail.com)
 * @since Dec 24, 2013
 */
public class Bootstrap
{
	private URL updateCheckURL;

	/**
	 * Constructor.
	 * 
	 * @param updateCheckURL
	 *            URL of changelog.xml
	 * @throws MalformedURLException
	 */
	public Bootstrap(String updateCheckURL) throws MalformedURLException
	{
		this.updateCheckURL = new URL(updateCheckURL);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			public void run()
			{
				cleanUp();
			}
		}));
	}

	/**
	 * Returns the remote build number from the changelog.xml.
	 * 
	 * @return The remote build number from the changelog.xml
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public int getRemoteBuildNumber() throws SAXException, IOException, ParserConfigurationException
	{
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(updateCheckURL.openStream()));
		doc.getDocumentElement().normalize();
		return Integer.parseInt(doc.getElementsByTagName("remoteBuildNumber").item(1).getTextContent());
	}

	/**
	 * Gets the changelog.xml file from the server, parses it to a {@link Document} object and returns it.
	 * 
	 * @return An XML document containing the changelog (and any other relevant information)
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public Document getChangelog() throws SAXException, IOException, ParserConfigurationException
	{
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(updateCheckURL.openStream()));
		doc.getDocumentElement().normalize();
		return doc;
	}

	/**
	 * Checks for an update.
	 * <p>
	 * Compares the version of the local file with the remote version (in changelog.xml).
	 * 
	 * @param localBuildNumber
	 *            Local build number
	 * @return True if update is needed, else false
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public boolean check(int localBuildNumber) throws SAXException, IOException, ParserConfigurationException
	{
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(updateCheckURL.openStream()));
		doc.getDocumentElement().normalize();
		if (doc.getElementsByTagName("*").item(2).getTextContent().compareTo("" + localBuildNumber) > 0)
			return true;
		return false;
	}

	/**
	 * Executes the update process.
	 * 
	 * @param f
	 *            Path to file that needs updating
	 * @param URL
	 *            URL to download remote file from
	 * @throws IOException
	 */
	public void update(final File f, String URL) throws IOException
	{
		File tempDownloadDir = new File(OSUtils.getDynamicStorageLocation() + "MooBootloader/temp/");
		final File tempDownloadFile = new File(tempDownloadDir.toString() + "/" + f.getName());
		tempDownloadDir.mkdirs();
		tempDownloadFile.createNewFile();
		DownloaderShell downloader = new DownloaderShell();
		new FrameDragger().applyTo(downloader);
		downloader.download(URL, tempDownloadFile.toString(), false);
		FileUtils.copyFile(tempDownloadFile, f);
		cleanUp();
	}

	private void cleanUp()
	{
		FileUtils.delete(new File(OSUtils.getDynamicStorageLocation() + "MooBootloader/"));
	}
}