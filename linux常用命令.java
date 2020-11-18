Linux下文件搜索、查找、查看命令{

一、根据 文件或目录名称 搜索
	find 【搜索目录】【-name或者-iname】【搜索字符】：-name和-iname的区别一个区分大小写，一个不区分大小写
	eg：在/etc 目录下搜索名字为init的文件或目录
	①、find /etc -name init (精准搜索，名字必须为 init 才能搜索的到)
	②、find /etc -iname init (精准搜索，名字必须为 init或者有字母大写也能搜索的到)
	③、find /etc -name *init (模糊搜索，以 init 结尾的文件或目录名)
	④、find /etc -name init??? (模糊搜索，？ 表示单个字符，即搜索到 init___)
二、根据 文件大小 搜索
	eg：在根目录下查找大于 100M 的文件
	find / -size +204800
	这里 +n 表示大于，-n 表示小于，n 表示等于
	1 数据块 == 512 字节 0.5KB，也就是1KB等于2数据块
	100MB == 102400KB204800数据块
三、根据 所有者和所属组 搜索
	①、在home目录下查询所属组为 root 的文件
	　　　　find /home -group root
	②、在home目录下查询所有者为 root 的文件
	　　　　find /home -user root
四、根据 时间属性 搜索
	find 【路径】【选项】【时间】
	选项有下面三种：-amin 访问时间
	　　　　　　　　 -cmin 文件属性被更改
	　　　　　　　　 -mmin 文件内容被修改
	时间：+n,-n,n分别表示超过n分钟，n分钟以内和n分钟
	eg：在 /etc 目录下查找5 分钟内被修改过属性的文件和目录
	　　　　find /etc -cmin -5
五、根据 文件类型或i节点 搜索
　-type 根据文件类型查找：
　f表示文件，d表示目录，l表示软链接
	eg：查找 /home 目录下文件类型是目录的
	find /home -type d
	　　-inum 根据i节点查找
	eg：查找 /tmp 目录下i节点为400342的文件或目录
	　 find /tmp -inum 400342
六、组合条件 搜索　　
　　这里有两个参数：
　　①、-a 表示两个条件同时满足（and）
　　②、-o 表示两个条件满足任意一个即可（or）
　　范例：查找/etc目录下大于80MB同时小于100MB的文件
　　find /etc -size +163840 -a -size -204800

2、在文件资料中查找文件：locate

	语法：locate【文件名】 -i 不区分大小写
	注意：这里和 find 命令是有区别的，find是全盘检索，而locate 是在文件资料库中进行搜索。所以locate命令的执行要比find命令执行速度快很多。但是这里有个问题，文件资料库是需要不断更新的。我们新创建的文件如果不更新 文件资料库，使用 locate 是查找不到的。
	updatedb 手动更新资料库，但是对于/tmp目录下的新建文件，是更新不到文件资料库的，因为/tmp目录不属于文件资料库的收录范围。
	eg：locate hcf
	在这里插入图片描述

3、搜索命令所在的目录及别名信息：which

	功能描述：搜索命令所在的目录及别名信息
	　语法：which【命令】
	　eg：which ls
	　在这里插入图片描述

4、搜索命令所在的目录及帮助文档路径：whereis**

	功能描述：搜索命令所在的目录及帮助文档路径
	　语法：whereis【命令】
	　eg：whereis ls
	　在这里插入图片描述

5、在文件中搜寻字符串匹配的行并输出：grep

	功能描述：在文件中搜寻字符串匹配的行并输出
	　语法：grep -iv 【指定字符串】【文件】
	　　　　　　　　-i 不区分大小写
	　　　　　　　　-v 排除指定字符串
	　eg：查找 /root/install.log 文件中包含 mysql 字符串的行，并输出
	　　　　grep mysql /root/install.log
	本搜索工具，根据用户指定的模式，对目标文件逐行进行匹配检查，打印匹配到的行
	grep是在文件中搜索匹配的字符串，是在文件中进行内容搜索，这个命令后面用到的比较多

6、分页显示一个文件或任何输出结果：more

	描述：
	分页显示一个文件或任何输出结果
	用于查看纯文本文件(较长的)格式
	格式：
	more[选项] 文件

7、分页显示一个文件并且可以回头：less

	less 与 more 类似，但使用 less 可以随意浏览文件，而 more 仅能向前移动，却不能向后移动，而且 less 在查看之前不会加载整个文件。

8、显示前几行文件内容：head

	head[必要参数][选择参数][文件]
	用于显示指定文件开始多少行内容
	命令参数：
	-n 10 显示前10行
	-n -10 正常输出但不显示最后的10行
	eg：显示new.txt的前两行内容
	head -n 2 new.txt
	head -2 new.txt

9、指定显示文件后多少行内容：tail

	tail[必要参数][选择参数][文件]
	用于显示指定文件末尾多少行内容
	命令参数：
	-n 10 显示后面10行
	-f 持续刷新显示的内容
	eg：显示new.txt的末尾两行内容
	tail -n 2 new.txt
	tail -2 new.txt
	eg：指定从第二行开始显示
	tail -n +2 new.txt

10、查看一个文件：cat

	描述：一次显示整个文件内容cat 命令 用于查看纯文本文件(较短)at [选项] [文件]

11、查看文件内容多少字符多少行多少字节：wc

	描述：wc 命令默认情况下会打印换行符数、单词数和字符数。用法：wc [选项] [文件]

12、排序文件内容：sort

	用法：sort [选项] [文件]eg：sort -b h.txt
}
Linux查看进程的4种方法{
	
第一种：ps aux

第二种：ps -elf  ps axms  // 大部分跟第一种一样，PPID为父进程的PID。

第三种：top

第四种：pstree -aup 可以带上|grep  查询特定进程。例如 pstree -aup | grep php
 
}











