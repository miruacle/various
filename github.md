1.添加ssh key到github，并检验是否添加成功。
linux生成ssh key:ssh-keygen -t rsa 
ssh -T git@github.com

2.关联本地项目到github，git remote add origin <git@github.com:username/project.git>

3.git配置:git config -global user.name/email "name/email" 

4.修改输出日志方式：git config --global alias.lg "log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit --date=relative"

5.修改git命令行着色：git config --global color.ui true
查看每次提交所引入的具体修改:git log -p
对比两个分支的提交：git log branchA --not branchB或git log branchA..branchB

6.开发分支一些规则：
首先要有两个分支：master:永远都处在即将发布状态(production-ready)；develog:最新的开发状态。这两个分支大部分情况保持一致，上线前测试阶段develope代码多一些，测试没问题，发布代码时和并到master。
辅助分支：feature:基于develope开发新功能的分支，完成后merge到develope。
and so on.
具体流程可以参考使用git-flow。

7.git stash 命令，代码写一半需要切换分支，然后需要不提交并且能保持当前状态的方法。当再次切换回本分支时，使用git stash apply还原。然后删除本次stash记录：git stash drop。或者git pop会还原并删除。

8.获取本地没有的远程分支git checkout -b branch-name origin/branch-name

/***********************************************************************************
*Git 基础
************************************************************************************/
1.每次提交更新/保存项目状态时，对全部文件做快照并保存其索引。

2.git有三种状态：已提交(commited)，已修改(modified)，已暂存(staged)。对应了三个工作区域：仓库(Repository)，工作目录(Working Directory)，暂存区(Staging Area)。

3.工作流程：在工作目录修改文件；暂存文件，将快照流放入暂存区；提交更新，找到暂存区的文件，将快照永久性存储到仓库。

4.初次运行git需要的配置：git config设置控制git的外观和行为配置变量。这些变量存储在三个不同的位置，/etc/gitconfig文件包含系统上每个用户及其仓库的通用配置，如果使用带有--system选项的git config时，从此文件读写配置变量；~/.gitconfig或~/.config/git/config文件只针对当前用户。可以传递--global选项让git读此文件；仓库目录中的config文件即.git/config只针对该仓库。

5.配置用户名和邮件地址

6.#git/github帮助频道 irc.freenode.net

7.gitignore文件：*.[oa]忽略所有以.o/a结尾的文件。*.~忽略以～结尾的文件（编辑器产生的副本）。格式规范：

    所有空行或者以 ＃ 开头的行都会被 Git 忽略。

    可以使用标准的 glob 模式匹配。

    匹配模式可以以（/）开头防止递归。

    匹配模式可以以（/）结尾指定目录。

    要忽略指定模式以外的文件或目录，可以在模式前加上惊叹号（!）取反。

8.glob 模式是指 shell 所使用的简化了的正则表达式。 星号（*）匹配零个或多个任意字符；[abc] 匹配任何一个列在方括号中的字符（这个例子要么匹配一个 a，要么匹配一个 b，要么匹配一个 c）；问号（?）只匹配一个任意字符；如果在方括号中使用短划线分隔两个字符，表示所有在这两个字符范围内的都可以匹配（比如 [0-9] 表示匹配所有 0 到 9 的数字）。 使用两个星号（*) 表示匹配任意中间目录，比如`a/**/z` 可以匹配 a/z, a/b/z 或 `a/b/c/z`等。

9.Tip
GitHub 有一个十分详细的针对数十种项目及语言的 .gitignore 文件列表，你可以在 https://github.com/github/gitignore 找到它.

10.git diff查看尚未暂存的改动（非上次提交之后的改动）。
   git diff --cached/staged查看已经暂存等待添加到下次提交里的内容。

11.git commit -a 自动把跟踪过的文件暂存后一起提交。

12.git rm --cached <file> 取消跟踪文件，但保留在工作区。

13.git mv file_from file_to 重命名文件

14.git log -p -n。 -p，用来显示每次提交的内容差异。-num 来仅显示最近n次提交。

15.git commit --amend 这个命令会将暂存区中的文件提交。 如果自上次提交以来你还未做任何修改（例如，在上次提交后马上执行了此命令），那么快照会保持不变，而你所修改的只是提交信息。

16.git reset HEAD <file> 取消暂存。将暂存区的文件撤回到工作目录。

17.git checkout -- [file] 是一个危险的命令,你对那个文件做的任何修改都会消失。(那么问题来了，如果你想要保留这些修改，但是还需要撤销修改，怎么办？）

18.git remote -v 会显示需要读写远程仓库使用的 Git 保存的简写与其对应的 URL。

19.git remote add <shortname> <url> 添加一个新的远程 Git 仓库，同时指定一个你可以轻松引用的简写。

20.git fetch [remote-name]这个命令会访问远程仓库，从中拉取所有你还没有的数据。 执行完成后，你将会拥有那个远程仓库中所有分支的引用，可以随时合并或查看。抓取的是克隆（或上一次抓取）后新推送的所有工作。 必须注意 git fetch 命令会将数据拉取到你的本地仓库 - 它并不会自动合并或修改你当前的工作。git pull会抓取并自动和并到当前所在的分支。

21.git remote show [remote-name]获取远程仓库更多的信息。这个命令列出了当你在特定的分支上执行 git push 会自动地推送到哪一个远程分支。执行 git pull 时哪些分支会自动合并。

22.git remote rename oldname newname重命名远程仓库的简写名。

23.git remote rm name移除远程仓库。

24.tag标签用法：chapter2.6

25.git config --global alias.command 'real command'，创建别名，方便使用。

/***********************************************************************************
*Git 分支
************************************************************************************/
1.git保存的不是文件的变化或差异，而是一些列不同时刻的文件快照。

假设现在有一个工作目录，里面包含了三个将要被暂存和提交的文件。 暂存操作会为每一个文件计算校验和，然后会把当前版本的文件快照保存到 Git 仓库中（Git 使用 blob 对象来保存它们），最终将校验和加入到暂存区域等待提交。

当使用 git commit 进行提交操作时，Git 会先计算每一个子目录的校验和，然后在 Git 仓库中这些校验和保存为树对象。随后，Git 便会创建一个提交对象，它除了包含上面提到的那些信息外，还包含指向这个树对象（项目根目录）的指针。

现在，Git 仓库中有五个对象：三个 blob 对象（保存着文件快照）、一个树对象（记录着目录结构和 blob 对象索引）以及一个提交对象（包含着指向前述树对象的指针和所有提交信息）。

2.分支和并，冲突解决：git merge branchname 提示冲突，git status查看冲突文件， 修改冲突， git add conflictfiles 标记冲突已解决，git commit完成合并提交。（git mergetool可以使用可视化工具）

3.分支开发工作流：
稳定分支
master分支保留完全稳定的代码（已发布/即将发布的代码）。
develope分支为master的平行分支，用来做后续开发/稳定性测试。达到目的后合并到master。

特性分支：短期分支，用于实现单一特性或相关工作。如iss,hotfix等。

4.远程分支
origin/master 或者其他远程分支，表示的是你最后一次与远程仓库 origin 通信时 master/其他 分支的状态。
origin/branch是存储在本地的，当远程版本库master分支有新的提交时，本地origin/master分支指针并不会移动。如果需要同步，可以使用git fetch origin，从远程版本库抓取本地没有的数据，更新本地数据库并移动origin/master指针指向新的位置。

5.跟踪分支
git checkout -b [branch] [remotename]/[branch]
设置已有的本地分支跟踪一个刚刚拉取下来的远程分支，或者想要修改正在跟踪的上游分支
git branch -u或--set-upstream-to [origin]/[branch]

6.删除远程分支
git push origin --delete [branch]

7.变基
首先找到两个分支的最近共同提交，然后对比当前分支相对于该提交的历次提交，提取相应的修改并存为临时文件，然后将当前分支指向目标基底，最后以此将之前另存为临时文件的修改依序应用。
步骤：topicbranch上执行git rebase [basebranch];切换至basebranch执行git merge [topicbranch]。或者basebranch上执行git rebase [basebranch] [topicbranch];完成后执行git merge [topicbranch]。
可以参考chapter3.6：有趣的变基例子

原则是，不要对在你的仓库外有副本的分支执行变基。只对尚未推送或分享给别人的本地修改执行变基操作清理历史，从不对已推送至别处的提交执行变基操作。

8.在服务器上搭建git(chapter 4.2)

/***********************************************************************************
*分布式git工作流程
************************************************************************************/
1.集中式工作流
一个中心集线器，或者说仓库，可以接受代码，所有人将自己的工作与之同步。 若干个开发者则作为节点——也就是中心仓库的消费者——并且与其进行同步。

2.集成管理者工作流
每个开发者拥有自己仓库的写权限和其他所有人仓库的读权限。 这种情形下通常会有个代表`‘官方’'项目的权威的仓库。 要为这个项目做贡献，你需要从该项目克隆出一个自己的公开仓库，然后将自己的修改推送上去。 接着你可以请求官方仓库的维护者拉取更新合并到主项目。 维护者可以将你的仓库作为远程仓库添加进来，在本地测试你的变更，将其合并入他们的分支并推送回官方仓库。

3.chapter 5.2私有小型团队

4.尝试新特性时，最好创建附带命名空间的特性分支，比如贡献者的id简写+描述特性的单词。

5.应用来自邮件的补丁：
git diff 或Unix diff创建的补丁git apply /tmp/patch-name-feture.patch
git format-patch生成的补丁git am name.patch

6.git diff master...branchA显示自当前特性分支与 master 分支的共同祖先起，该分支中的工作

7.拣选：git cherry-pick commit-id 将特定的某次提交变基，然后重新应用到当前分支。

8.为发布打标签：当你决定进行一次发布时，你可能想要留下一个标签，这样在之后的任何一个提交点都可以重新创建该发布。

9.github流程：


    从 master 分支中创建一个新分支

    提交一些修改来改进项目

    将这个分支推送到 GitHub 上

    创建一个合并请求

    讨论，根据实际情况继续修改

    项目的拥有者合并或关闭你的合并请求




几个注意事项：
1.提交说明规范
2.分支和提交流程按照推荐路线
3.
