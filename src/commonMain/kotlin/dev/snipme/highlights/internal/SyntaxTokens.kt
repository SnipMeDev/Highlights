package dev.snipme.highlights.internal

internal object SyntaxTokens {

    val C_KEYWORDS = """
        auto,break,case,char,const,continue,default,do,double,else,enum,extern,float,for,goto,if,
        int,long,register,return,short,signed,sizeof,static,struct,switch,typedef,union,unsigned,
        void,volatile,while
    """.trimIndent().split(",")

    val CPP_KEYWORDS = """
        asm,auto,bool,break,case,catch,char,class,const,const_cast,continue,default,delete,do,
        double,dynamic_cast,else,enum,explicit,export,extern,false,float,for,friend,goto,if,inline,
        int,long,mutable,namespace,new,operator,private,protected,public,register,reinterpret_cast,
        return,short,signed,sizeof,static,static_cast,struct,switch,template,this,throw,true,try,
        typedef,typeid,typename,union,unsigned,using,virtual,void,volatile,wchar_t,while
    """.trimIndent().split(",")

    val JAVA_KEYWORDS = """
        abstract,assert,boolean,break,byte,case,catch,char,class,const,continue,default,do,double,
        else,enum,extends,final,finally,float,for,goto,if,implements,import,instanceof,int,
        interface,long,native,new,null,package,private,protected,public,return,short,static,
        strictfp,super,switch,synchronized,this,throw,throws,transient,try,void,volatile,while
    """.trimIndent().split(",")

    val KOTLIN_KEYWORDS = """
        actual,abstract,annotation,as,as?,break,by,catch,class,companion,const,constructor,continue,
        coroutine,crossinline,data,delegate,dynamic,do,else,enum,expect,external,false,final,
        finally,for,fun,get,if,import,in,!in,infix,inline,interface,internal,is,!is,lazy,lateinit,
        native,null,object,open,operator,out,override,package,private,protected,public,reified,
        return,sealed,set,super,suspend,tailrec,this,throw,true,try,typealias,typeof,val,var,vararg,
        when,while,yield
    """.trimIndent().split(",")

    val RUST_KEYWORDS = """
        as,async,await,break,const,continue,crate,dyn,else,enum,extern,false,fn,for,if,impl,in,let,
        loop,match,mod,move,mut,pub,ref,return,Self,self,static,struct,super,trait,true,type,union,
        unsafe,use,where,while,abstract,become,box,do,final,macro,override,priv,try,typeof,
        unsized,virtual,yield
    """.trimIndent().split(",")

    val CSHARP_KEYWORDS = """
        abstract,as,base,bool,break,byte,case,catch,char,checked,class,const,continue,decimal,
        default,delegate,do,double,else,enum,event,explicit,extern,false,finally,fixed,float,for,
        foreach,goto,if,implicit,in,int,interface,internal,is,lock,long,namespace,new,null,object,
        operator,out,override,params,private,protected,public,readonly,ref,return,sbyte,sealed,
        short,sizeof,stackalloc,static,string,struct,switch,this,throw,true,try,typeof,uint,ulong,
        unchecked,unsafe,ushort,using,virtual,void,volatile,while
    """.trimIndent().split(",")

    val COFFEE_SCRIPT_KEYWORDS = """
        =,->,Infinity,NaN,and,arguments,await,break,by,case,catch,class,continue,debugger,delete,
        defer,default,do,else,export,extends,false,finally,for,function,if,import,in,instanceof,is,
        isnt,let,loop,new,no,not,null,of,on,or,package,return,super,switch,this,throw,true,try,
        typeof,unless,undefined,var,wait,when,with,yield
    """.trimIndent().split(",")

    val JAVASCRIPT_KEYWORDS = """
        async,await,boolean,break,case,catch,class,const,continue,debugger,default,delete,do,else,
        enum,export,extends,false,finally,for,function,if,implements,import,in,instanceof,interface,
        let,new,null,package,private,protected,public,return,super,switch,this,throw,true,try,typeof,
        var,void,while,with,yield
    """.trimIndent().split(",")

    val PERL_KEYWORDS = """
        __DATA__,__END__,__FILE__,__LINE__,__PACKAGE__,and,cmp,continue,do,else,elsif,eq,eval,for,
        foreach,goto,gt,if,last,last,le,lt,my,ne,next,no,not,or,package,redo,ref,return,sub,unless,
        until,use,while,xor
    """.trimIndent().split(",")

    val PYTHON_KEYWORDS = """
        False,True,and,as,assert,async,await,break,class,continue,def,del,elif,else,except,finally,
        for,from,global,if,import,in,is,lambda,nonlocal,not,or,pass,raise,return,try,while,with,
        yield
    """.trimIndent().split(",")

    val RUBY_KEYWORDS = """
        __ENCODING__,__END__,__FILE__,__LINE__,BEGIN,END,alias,and,begin,break,case,class,def,
        defined?,do,else,elsif,end,ensure,false,for,if,in,module,next,nil,not,or,redo,rescue,retry,
        return,self,super,then,true,undef,unless,until,when,while,yield
    """.trimIndent().split(",")

    val SH_KEYWORDS = """
        alias,bg,bind,break,builtin,caller,cd,command,compgen,complete,compopt,continue,declare,
        dirs,disown,echo,enable,eval,exec,exit,export,fc,fg,getopts,hash,help,history,jobs,kill,let,
        local,logout,popd,printf,pushd,pwd,read,readonly,return,set,shift,shopt,source,suspend,test,
    """.trimIndent()

    val SWIFT_KEYWORDS = FLOW_CONTROL_KEYWORDS + "," +
            "associatedtype,async,await,class,deinit,enum,extension,fileprivate," +
            "func,import,init,inout,internal,let,open,operator,private,protocol,public,rethrows,static," +
            "struct,subscript,typealias,andvar,case,default,defer,fallthrough," +
            "guard,in,repeat,switch,where,as,Any,catch,false,is,nil,super,self,Self," +
            "throw,throws,true,try,#available,#colorLiteral,#column,#else,#elseif,#endif,#error,#file," +
            "#fileID,#fileLiteral,#filePath,#function,#if,#imageLiteral,#line,#selector,#sourceLocation," +
            "#warning,associativity,convenience,dynamic,didSet,final,get,infix,indirect,lazy,left," +
            "mutating,none,nonmutating,optional,override,postfix,precedence,prefix,Protocol,required," +
            "right,set,Type,unowned,weak,willSet,var,_".split(",")

    // TODO Add Dart, GO, PHP, Typescript,  language

    val ALL_KEYWORDS = (CPP_KEYWORDS + KOTLIN_KEYWORDS + CSHARP_KEYWORDS
            + RUST_KEYWORDS + COFFEE_SCRIPT_KEYWORDS
            + JAVASCRIPT_KEYWORDS + PERL_KEYWORDS + PYTHON_KEYWORDS + RUBY_KEYWORDS
            + SH_KEYWORDS + SWIFT_KEYWORDS)

    val ALL_MIXED_KEYWORDS: List<String> =
        """#available #column #define #defined #elif #else #else#elseif #endif #error #file #function
    #if #ifdef #ifndef #include #line #pragma #selector #undef abstract add after alias
    alignas alignof and and_eq andalso as ascending asm assert associatedtype associativity
    async atomic_cancel atomic_commit atomic_noexcept auto await base become begin bitand
    bitor bnot bor box break bsl bsr bxor case catch chan
    checked
    class compl concept cond const const_cast constexpr continue convenience
    covariant crate debugger decltype def default defer deferred defined? deinit
    del delegate delete descending didset div do dynamic dynamic_cast dynamictype
    elif else elseif elsif end ensure eval event except explicit export extends extension
    extern external factory fallthrough false final finally fixed fn for foreach friend
    from
    fun func function get global go goto group guard if impl implements implicit import
    in indirect infix init inline inout instanceof
    interface internal into is join lambda
    lazy left let library local lock long loop macro map match mod module move mut mutable
    mutating namespace native new next nil noexcept none nonlocal nonmutating not not_eq
    null nullptr
    object of offsetof operator optional or or_eq orderby orelse out override
    package params part partial pass postfix precedence prefix priv private proc protected
    protocol pub public pure raise range readonly receive redo ref register reinterpret_cast
    rem remove repeat required requires rescue rethrow rethrows retry return right sbyte
    sealed select self set short signed sizeof stackalloc static static_assert static_cast
    strictfp struct subscript super switch sync synchronized template then this
    thread_local throw throws trait transaction_safe transaction_safe_dynamic transient
    true try type
    typealias typedef typeid typename typeof uint ulong unchecked undef
    union unless unowned unsafe unsigned unsized until use ushort using
    value var virtual
    void volatile wchar_t weak when where while willset with xor xor_eq xorauto yield
    yieldabstract yieldarguments
    val list
        override get
        setas as? in !in !is is by
        constructor delegate dynamic field file init param property receiver setparam data
        data expect lateinit crossinline companion annotation actual noinline open reified
        suspend tailrec vararg it constraint alter column table all any asc backup database
        between check create index replace view procedure unique desc distinct drop exec
        exists foreign key full outer having inner insert like limit order primary rownum
        top truncate update values"""
            .split(" ")

    // TODO Migrate to list of chars
    val TOKEN_DELIMITERS =
        listOf(" ", ",", ".", ":", ";", "(", ")", "=", "{", "}", "<", ">", "\r", "\n")
    val STRING_DELIMITERS = listOf("\'", "\"", "\"\"\"")
    val COMMENT_DELIMITERS = listOf("//", "#")

    // TODO Add support for other other languages like Dart or Python
    val MULTILINE_COMMENT_DELIMITERS = listOf(Pair("/*", "*/"))
    val PUNCTUATION_CHARACTERS = listOf(",", ".", ":", ";")
    val MARK_CHARACTERS = listOf("(", ")", "=", "{", "}", "<", ">", "-", "+", "[", "]", "|", "&")
}

