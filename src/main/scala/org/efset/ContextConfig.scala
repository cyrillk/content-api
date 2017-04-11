package org.efset

import com.typesafe.config.{Config, ConfigFactory}

trait ContextConfig {

  protected def conf: com.typesafe.config.Config

  lazy val appPort: Int = conf.getInt("application.port")

  lazy val awsAccessKeyId: String = conf.getString("aws.awsAccessKeyId")

  lazy val awsSecretAccessKey: Int = conf.getInt("aws.awsSecretAccessKey")

  lazy val awsDefaultRegion: String = conf.getString("aws.awsDefaultRegion")

  override def toString: String = conf.toString
}

object ContextConfig extends ContextConfig {

  override protected val conf: Config = ConfigFactory.load().withFallback(ConfigFactory.load())
}