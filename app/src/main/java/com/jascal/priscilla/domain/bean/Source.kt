package com.jascal.priscilla.domain.bean

/**
 * @author jascal
 * @time 2018/8/22
 * describe
 */
interface Source<out T> {
  fun obtain(url: String): T
}