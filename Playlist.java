import java.util.*;

public class Playlist {

	/** Finds the maximum possible length of playlist that can be constructed
	*   within a specified number of seconds from a list of video play times
	*   @param  videos   An array containing individual video play times
	*   @param  maxTime  The maximum permissible length of playlist (seconds)
	*   @return          The maximum possible length of playlist from the times supplied (seconds)
	*/
	public static int calculateMaxLength(int[] videos, int maxTime) {

		int n = videos.length;
		int[][] table = new int[maxTime+1][n+1];
		int currentTime;
		for (int t=0; t <= maxTime; t++) {
			for (int i=1; i <= n; i++) {
				currentTime = table[t][i-1];
				if (videos[i-1] > t)
					table[t][i] = currentTime;
				else
					table[t][i] = Math.max(
						currentTime,
						videos[i-1] + table[t-videos[i-1]][i-1]
					);
				System.out.println("["+ t +"]["+ i +"] "+ table[t][i]);
			}
		}
		return table[maxTime][n];
	}

	/** Finds the videos that make up the longest playlist that can be constructed
	*   within a specified number of seconds from a list of video play times
	*   @param  videos   An array containing individual video play times
	*   @param  maxTime  The maximum permissible length of playlist (seconds)
	*   @return          The maximum possible length of playlist from the times supplied (seconds)
	*/
	public static ArrayDeque<Integer> getPlaylist(int[] videos, int maxTime) {

		int n = videos.length;
		int[][] table = new int[maxTime+1][n+1];
		int currentTime;
		for (int t=0; t <= maxTime; t++) {
			for (int i=1; i <= n; i++) {
				currentTime = table[t][i-1];
				if (videos[i-1] > t)
					table[t][i] = currentTime;
				else
					table[t][i] = Math.max(
						currentTime,
						videos[i-1] + table[t-videos[i-1]][i-1]
					);
				System.out.println("["+ t +"]["+ i +"] "+ table[t][i]);
			}
		}
		ArrayDeque<Integer> playlist = new ArrayDeque<>(n);
		int t = maxTime;
		int i = n;
		while (table[t][i] > 0) {
			if (table[t][i] != table[t][i-1]) {
				playlist.push(videos[i-1]);
				t -= videos[i-1];
			}
			i--;
		}
		return playlist;
	}

	public static void main(String[] args) {

		int maxTime = args.length > 0 ? Integer.parseInt(args[0]) : 30;
		int[] videos = {31,23,17,11,5,2,3,7,13,19,29};
		System.out.println("Max length: "+ getPlaylist(videos, maxTime));

	}
}
