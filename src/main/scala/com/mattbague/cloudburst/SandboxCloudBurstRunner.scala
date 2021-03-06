package com.mattbague.cloudburst

import com.typesafe.scalalogging.LazyLogging

object SandboxCloudBurstRunner extends App with LazyLogging {

  val userDirectory = sys.props.get("user.home").getOrElse(".")
  logger.info(s"Using $userDirectory for downloads")

  val clientIdOverride = sys.props.get("client_id")

  val wiring = new Wiring(userDirectory, userDirectory + "/soundcloud")

  val apiClient = wiring.apiClient

  //  val result = apiClient.getTrackById()
    val result = apiClient.getTrackByUrl("https://soundcloud.com/plexitofer/cupid-groove")
//    val result = apiClient.getTrackByUrl("https://soundcloud.com/skibblez/sky-romance")
    apiClient.downloadTrack(result.get)

  val userId = 269568808

//  val userLikeTracks = apiClient.getUserLikesById(userId)

//  val totalTracks = userLikeTracks.size

//  userLikeTracks.headOption.zipWithIndex.foreach { case (track, ndx) =>
//    logger.info(s"Processing track $ndx of $totalTracks")
//    apiClient.downloadTrack(track)
//  }
  //  println(JsonUtil.toJson(result, true))

  wiring.programLocalStorageService.shutdown()

  System.exit(0)
}
