LightCompiler
=============

プログラミングを始めて3ヶ月の文系の学生が対象の授業で使うために作成したコンパイラ．
目的は，コンパイラがどんな処理をするのかを，90分の授業1コマで，イメージできるようにすること．

コンパイラが処理できるのは，演算子がない整数，あるいは，整数同士の足し算．
すなわち，"1", "1+1" の形をした式だけ．


上記の前提をの下で設計しているので，
字句解析でのオートマトン，構文解析での文脈自由文法，
などの方法は使用しない．

各フェーズでの処理内容

1. 字句解析
トークン列の作成．　　（ArrayList型のリストにトークンを追加する．）
　- "+"が無い場合
　  文字列をリストに追加
 - "+"がある場合
 　　"+"の前後の数字e1,e2を取り出し，e1,"+",e2をリストに追加する．

2. 構文解析
 - "+"が無い場合
　  trueを返す
 - "+"がある場合
 　　リストの2つ目の要素が"+"ならば，true，さもなければ，falseを返す．

3. 意味解析
　演算子以外が整数型ならばtrueを返す．

4. コード最適化
 - "+"が無い場合
　  何もしない
 - "+"がある場合
 　　2つの整数を足し算する．

5. コード生成
　標準出力する．
