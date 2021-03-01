package in.handyman.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import in.handyman.services.DslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'es-update'", "'as'", "'from'", "'to'", "'with-fetchsize-as'", "'and-writesize-as'", "'using'", "'on-condition'", "'with-write-threads'", "'es-accumulate'", "'efblc'", "'secured-by'", "'with-key'", "'through-user'", "'for-campaign'", "'into'", "'elasticget'", "'java'", "'name-sake-db'", "'fbdp'", "'on'", "'auth-by'", "'for-group'", "'from-source'", "'with-class'", "'fbrn'", "'smsleadssms'", "'with-sender'", "'through-account'", "'do-dryrun-with'", "'abort'", "'gcontact-fetchall'", "'for-project'", "'on-behalf-of'", "'to-target'", "'sendmail'", "'gcontact'", "'gcalendar'", "'fblc'", "'fbfd'", "'for-form'", "'dropfile'", "'in-path'", "'doozle'", "'in-table'", "'rest'", "'with-url'", "'and-method-as'", "'update-url-with'", "'update-header-with'", "'update-body-with'", "'parent'", "'store-ack-at'", "'part'", "'with'", "'trelloget'", "'from-board'", "'trelloput'", "'for-list'", "'assign'", "'source'", "'callprocess'", "'with-target'", "'from-file'", "'for-every'", "'forkprocess'", "'log'", "'logto'", "'in'", "'c2sms'", "'with-user'", "'slackput'", "'copydata'", "'writecsv'", "'loadcsv'", "'transform'", "'if'", "'<'", "'>'", "'=='", "'contains'"
    };
    public static final int T__50=50;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=5;
    public static final int RULE_INT=6;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;

    // delegates
    // delegators


        public InternalDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalDsl.g"; }



     	private DslGrammarAccess grammarAccess;

        public InternalDslParser(TokenStream input, DslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Process";
       	}

       	@Override
       	protected DslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleProcess"
    // InternalDsl.g:64:1: entryRuleProcess returns [EObject current=null] : iv_ruleProcess= ruleProcess EOF ;
    public final EObject entryRuleProcess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcess = null;


        try {
            // InternalDsl.g:64:48: (iv_ruleProcess= ruleProcess EOF )
            // InternalDsl.g:65:2: iv_ruleProcess= ruleProcess EOF
            {
             newCompositeNode(grammarAccess.getProcessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcess=ruleProcess();

            state._fsp--;

             current =iv_ruleProcess; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcess"


    // $ANTLR start "ruleProcess"
    // InternalDsl.g:71:1: ruleProcess returns [EObject current=null] : (otherlv_0= 'process' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= '{' ( (lv_try_3_0= ruleTry ) ) ( (lv_catch_4_0= ruleCatch ) ) ( (lv_finally_5_0= ruleFinally ) ) otherlv_6= '}' ) ;
    public final EObject ruleProcess() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        EObject lv_try_3_0 = null;

        EObject lv_catch_4_0 = null;

        EObject lv_finally_5_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:77:2: ( (otherlv_0= 'process' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= '{' ( (lv_try_3_0= ruleTry ) ) ( (lv_catch_4_0= ruleCatch ) ) ( (lv_finally_5_0= ruleFinally ) ) otherlv_6= '}' ) )
            // InternalDsl.g:78:2: (otherlv_0= 'process' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= '{' ( (lv_try_3_0= ruleTry ) ) ( (lv_catch_4_0= ruleCatch ) ) ( (lv_finally_5_0= ruleFinally ) ) otherlv_6= '}' )
            {
            // InternalDsl.g:78:2: (otherlv_0= 'process' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= '{' ( (lv_try_3_0= ruleTry ) ) ( (lv_catch_4_0= ruleCatch ) ) ( (lv_finally_5_0= ruleFinally ) ) otherlv_6= '}' )
            // InternalDsl.g:79:3: otherlv_0= 'process' ( (lv_name_1_0= RULE_STRING ) ) otherlv_2= '{' ( (lv_try_3_0= ruleTry ) ) ( (lv_catch_4_0= ruleCatch ) ) ( (lv_finally_5_0= ruleFinally ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getProcessAccess().getProcessKeyword_0());
            		
            // InternalDsl.g:83:3: ( (lv_name_1_0= RULE_STRING ) )
            // InternalDsl.g:84:4: (lv_name_1_0= RULE_STRING )
            {
            // InternalDsl.g:84:4: (lv_name_1_0= RULE_STRING )
            // InternalDsl.g:85:5: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getProcessAccess().getNameSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProcessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getProcessAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalDsl.g:105:3: ( (lv_try_3_0= ruleTry ) )
            // InternalDsl.g:106:4: (lv_try_3_0= ruleTry )
            {
            // InternalDsl.g:106:4: (lv_try_3_0= ruleTry )
            // InternalDsl.g:107:5: lv_try_3_0= ruleTry
            {

            					newCompositeNode(grammarAccess.getProcessAccess().getTryTryParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_6);
            lv_try_3_0=ruleTry();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcessRule());
            					}
            					set(
            						current,
            						"try",
            						lv_try_3_0,
            						"in.handyman.Dsl.Try");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalDsl.g:124:3: ( (lv_catch_4_0= ruleCatch ) )
            // InternalDsl.g:125:4: (lv_catch_4_0= ruleCatch )
            {
            // InternalDsl.g:125:4: (lv_catch_4_0= ruleCatch )
            // InternalDsl.g:126:5: lv_catch_4_0= ruleCatch
            {

            					newCompositeNode(grammarAccess.getProcessAccess().getCatchCatchParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_7);
            lv_catch_4_0=ruleCatch();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcessRule());
            					}
            					set(
            						current,
            						"catch",
            						lv_catch_4_0,
            						"in.handyman.Dsl.Catch");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalDsl.g:143:3: ( (lv_finally_5_0= ruleFinally ) )
            // InternalDsl.g:144:4: (lv_finally_5_0= ruleFinally )
            {
            // InternalDsl.g:144:4: (lv_finally_5_0= ruleFinally )
            // InternalDsl.g:145:5: lv_finally_5_0= ruleFinally
            {

            					newCompositeNode(grammarAccess.getProcessAccess().getFinallyFinallyParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_8);
            lv_finally_5_0=ruleFinally();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcessRule());
            					}
            					set(
            						current,
            						"finally",
            						lv_finally_5_0,
            						"in.handyman.Dsl.Finally");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getProcessAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcess"


    // $ANTLR start "entryRuleTry"
    // InternalDsl.g:170:1: entryRuleTry returns [EObject current=null] : iv_ruleTry= ruleTry EOF ;
    public final EObject entryRuleTry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTry = null;


        try {
            // InternalDsl.g:170:44: (iv_ruleTry= ruleTry EOF )
            // InternalDsl.g:171:2: iv_ruleTry= ruleTry EOF
            {
             newCompositeNode(grammarAccess.getTryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTry=ruleTry();

            state._fsp--;

             current =iv_ruleTry; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTry"


    // $ANTLR start "ruleTry"
    // InternalDsl.g:177:1: ruleTry returns [EObject current=null] : (otherlv_0= 'try' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' ) ;
    public final EObject ruleTry() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_action_3_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:183:2: ( (otherlv_0= 'try' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' ) )
            // InternalDsl.g:184:2: (otherlv_0= 'try' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' )
            {
            // InternalDsl.g:184:2: (otherlv_0= 'try' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' )
            // InternalDsl.g:185:3: otherlv_0= 'try' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getTryAccess().getTryKeyword_0());
            		
            // InternalDsl.g:189:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalDsl.g:190:4: (lv_name_1_0= RULE_ID )
            {
            // InternalDsl.g:190:4: (lv_name_1_0= RULE_ID )
            // InternalDsl.g:191:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getTryAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTryRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getTryAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalDsl.g:211:3: ( (lv_action_3_0= ruleAction ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==17||(LA1_0>=26 && LA1_0<=27)||(LA1_0>=33 && LA1_0<=34)||LA1_0==36||(LA1_0>=42 && LA1_0<=43)||(LA1_0>=47 && LA1_0<=48)||(LA1_0>=52 && LA1_0<=56)||LA1_0==58||LA1_0==60||LA1_0==62||LA1_0==72||LA1_0==74||LA1_0==76||LA1_0==78||(LA1_0>=82 && LA1_0<=83)||LA1_0==86||(LA1_0>=88 && LA1_0<=92)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalDsl.g:212:4: (lv_action_3_0= ruleAction )
            	    {
            	    // InternalDsl.g:212:4: (lv_action_3_0= ruleAction )
            	    // InternalDsl.g:213:5: lv_action_3_0= ruleAction
            	    {

            	    					newCompositeNode(grammarAccess.getTryAccess().getActionActionParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_action_3_0=ruleAction();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTryRule());
            	    					}
            	    					add(
            	    						current,
            	    						"action",
            	    						lv_action_3_0,
            	    						"in.handyman.Dsl.Action");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            otherlv_4=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getTryAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTry"


    // $ANTLR start "entryRuleFinally"
    // InternalDsl.g:238:1: entryRuleFinally returns [EObject current=null] : iv_ruleFinally= ruleFinally EOF ;
    public final EObject entryRuleFinally() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFinally = null;


        try {
            // InternalDsl.g:238:48: (iv_ruleFinally= ruleFinally EOF )
            // InternalDsl.g:239:2: iv_ruleFinally= ruleFinally EOF
            {
             newCompositeNode(grammarAccess.getFinallyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFinally=ruleFinally();

            state._fsp--;

             current =iv_ruleFinally; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFinally"


    // $ANTLR start "ruleFinally"
    // InternalDsl.g:245:1: ruleFinally returns [EObject current=null] : (otherlv_0= 'finally' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' ) ;
    public final EObject ruleFinally() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_action_3_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:251:2: ( (otherlv_0= 'finally' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' ) )
            // InternalDsl.g:252:2: (otherlv_0= 'finally' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' )
            {
            // InternalDsl.g:252:2: (otherlv_0= 'finally' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' )
            // InternalDsl.g:253:3: otherlv_0= 'finally' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getFinallyAccess().getFinallyKeyword_0());
            		
            // InternalDsl.g:257:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalDsl.g:258:4: (lv_name_1_0= RULE_ID )
            {
            // InternalDsl.g:258:4: (lv_name_1_0= RULE_ID )
            // InternalDsl.g:259:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFinallyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFinallyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getFinallyAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalDsl.g:279:3: ( (lv_action_3_0= ruleAction ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17||(LA2_0>=26 && LA2_0<=27)||(LA2_0>=33 && LA2_0<=34)||LA2_0==36||(LA2_0>=42 && LA2_0<=43)||(LA2_0>=47 && LA2_0<=48)||(LA2_0>=52 && LA2_0<=56)||LA2_0==58||LA2_0==60||LA2_0==62||LA2_0==72||LA2_0==74||LA2_0==76||LA2_0==78||(LA2_0>=82 && LA2_0<=83)||LA2_0==86||(LA2_0>=88 && LA2_0<=92)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalDsl.g:280:4: (lv_action_3_0= ruleAction )
            	    {
            	    // InternalDsl.g:280:4: (lv_action_3_0= ruleAction )
            	    // InternalDsl.g:281:5: lv_action_3_0= ruleAction
            	    {

            	    					newCompositeNode(grammarAccess.getFinallyAccess().getActionActionParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_action_3_0=ruleAction();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFinallyRule());
            	    					}
            	    					add(
            	    						current,
            	    						"action",
            	    						lv_action_3_0,
            	    						"in.handyman.Dsl.Action");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            otherlv_4=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getFinallyAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFinally"


    // $ANTLR start "entryRuleCatch"
    // InternalDsl.g:306:1: entryRuleCatch returns [EObject current=null] : iv_ruleCatch= ruleCatch EOF ;
    public final EObject entryRuleCatch() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCatch = null;


        try {
            // InternalDsl.g:306:46: (iv_ruleCatch= ruleCatch EOF )
            // InternalDsl.g:307:2: iv_ruleCatch= ruleCatch EOF
            {
             newCompositeNode(grammarAccess.getCatchRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCatch=ruleCatch();

            state._fsp--;

             current =iv_ruleCatch; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCatch"


    // $ANTLR start "ruleCatch"
    // InternalDsl.g:313:1: ruleCatch returns [EObject current=null] : (otherlv_0= 'catch' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' ) ;
    public final EObject ruleCatch() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_action_3_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:319:2: ( (otherlv_0= 'catch' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' ) )
            // InternalDsl.g:320:2: (otherlv_0= 'catch' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' )
            {
            // InternalDsl.g:320:2: (otherlv_0= 'catch' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}' )
            // InternalDsl.g:321:3: otherlv_0= 'catch' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_action_3_0= ruleAction ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getCatchAccess().getCatchKeyword_0());
            		
            // InternalDsl.g:325:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalDsl.g:326:4: (lv_name_1_0= RULE_ID )
            {
            // InternalDsl.g:326:4: (lv_name_1_0= RULE_ID )
            // InternalDsl.g:327:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getCatchAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCatchRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getCatchAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalDsl.g:347:3: ( (lv_action_3_0= ruleAction ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==17||(LA3_0>=26 && LA3_0<=27)||(LA3_0>=33 && LA3_0<=34)||LA3_0==36||(LA3_0>=42 && LA3_0<=43)||(LA3_0>=47 && LA3_0<=48)||(LA3_0>=52 && LA3_0<=56)||LA3_0==58||LA3_0==60||LA3_0==62||LA3_0==72||LA3_0==74||LA3_0==76||LA3_0==78||(LA3_0>=82 && LA3_0<=83)||LA3_0==86||(LA3_0>=88 && LA3_0<=92)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalDsl.g:348:4: (lv_action_3_0= ruleAction )
            	    {
            	    // InternalDsl.g:348:4: (lv_action_3_0= ruleAction )
            	    // InternalDsl.g:349:5: lv_action_3_0= ruleAction
            	    {

            	    					newCompositeNode(grammarAccess.getCatchAccess().getActionActionParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_action_3_0=ruleAction();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getCatchRule());
            	    					}
            	    					add(
            	    						current,
            	    						"action",
            	    						lv_action_3_0,
            	    						"in.handyman.Dsl.Action");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_4=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getCatchAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCatch"


    // $ANTLR start "entryRuleAction"
    // InternalDsl.g:374:1: entryRuleAction returns [EObject current=null] : iv_ruleAction= ruleAction EOF ;
    public final EObject entryRuleAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAction = null;


        try {
            // InternalDsl.g:374:47: (iv_ruleAction= ruleAction EOF )
            // InternalDsl.g:375:2: iv_ruleAction= ruleAction EOF
            {
             newCompositeNode(grammarAccess.getActionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAction=ruleAction();

            state._fsp--;

             current =iv_ruleAction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAction"


    // $ANTLR start "ruleAction"
    // InternalDsl.g:381:1: ruleAction returns [EObject current=null] : (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET | this_EsUpdate_28= ruleEsUpdate | this_EsAccumulate_29= ruleEsAccumulate ) ;
    public final EObject ruleAction() throws RecognitionException {
        EObject current = null;

        EObject this_Copydata_0 = null;

        EObject this_LoadCsv_1 = null;

        EObject this_WriteCsv_2 = null;

        EObject this_Transform_3 = null;

        EObject this_GooglecalPUT_4 = null;

        EObject this_SlackPUT_5 = null;

        EObject this_ClickSendSms_6 = null;

        EObject this_Updatedaudit_7 = null;

        EObject this_Callprocess_8 = null;

        EObject this_Forkprocess_9 = null;

        EObject this_Fetch_10 = null;

        EObject this_TrelloPUT_11 = null;

        EObject this_TrelloGET_12 = null;

        EObject this_Rest_13 = null;

        EObject this_Doozle_14 = null;

        EObject this_Dropfile_15 = null;

        EObject this_FBCLead_16 = null;

        EObject this_FBFormDownload_17 = null;

        EObject this_SendMail_18 = null;

        EObject this_GooglecontactPUT_19 = null;

        EObject this_GooglecontactSelectAll_20 = null;

        EObject this_Abort_21 = null;

        EObject this_SmsLeadSms_22 = null;

        EObject this_FirebaseReactiveNotification_23 = null;

        EObject this_FirebaseDatabasePut_24 = null;

        EObject this_ExecJava_25 = null;

        EObject this_ElasticFBCLead_26 = null;

        EObject this_ElasticGET_27 = null;

        EObject this_EsUpdate_28 = null;

        EObject this_EsAccumulate_29 = null;



        	enterRule();

        try {
            // InternalDsl.g:387:2: ( (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET | this_EsUpdate_28= ruleEsUpdate | this_EsAccumulate_29= ruleEsAccumulate ) )
            // InternalDsl.g:388:2: (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET | this_EsUpdate_28= ruleEsUpdate | this_EsAccumulate_29= ruleEsAccumulate )
            {
            // InternalDsl.g:388:2: (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET | this_EsUpdate_28= ruleEsUpdate | this_EsAccumulate_29= ruleEsAccumulate )
            int alt4=30;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt4=1;
                }
                break;
            case 91:
                {
                alt4=2;
                }
                break;
            case 90:
                {
                alt4=3;
                }
                break;
            case 92:
                {
                alt4=4;
                }
                break;
            case 54:
                {
                alt4=5;
                }
                break;
            case 88:
                {
                alt4=6;
                }
                break;
            case 86:
                {
                alt4=7;
                }
                break;
            case 83:
                {
                alt4=8;
                }
                break;
            case 78:
                {
                alt4=9;
                }
                break;
            case 82:
                {
                alt4=10;
                }
                break;
            case 76:
                {
                alt4=11;
                }
                break;
            case 74:
                {
                alt4=12;
                }
                break;
            case 72:
                {
                alt4=13;
                }
                break;
            case 62:
                {
                alt4=14;
                }
                break;
            case 60:
                {
                alt4=15;
                }
                break;
            case 58:
                {
                alt4=16;
                }
                break;
            case 55:
                {
                alt4=17;
                }
                break;
            case 56:
                {
                alt4=18;
                }
                break;
            case 52:
                {
                alt4=19;
                }
                break;
            case 53:
                {
                alt4=20;
                }
                break;
            case 48:
                {
                alt4=21;
                }
                break;
            case 47:
                {
                alt4=22;
                }
                break;
            case 43:
                {
                alt4=23;
                }
                break;
            case 42:
                {
                alt4=24;
                }
                break;
            case 36:
                {
                alt4=25;
                }
                break;
            case 34:
                {
                alt4=26;
                }
                break;
            case 27:
                {
                alt4=27;
                }
                break;
            case 33:
                {
                alt4=28;
                }
                break;
            case 17:
                {
                alt4=29;
                }
                break;
            case 26:
                {
                alt4=30;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalDsl.g:389:3: this_Copydata_0= ruleCopydata
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getCopydataParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Copydata_0=ruleCopydata();

                    state._fsp--;


                    			current = this_Copydata_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalDsl.g:398:3: this_LoadCsv_1= ruleLoadCsv
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getLoadCsvParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_LoadCsv_1=ruleLoadCsv();

                    state._fsp--;


                    			current = this_LoadCsv_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalDsl.g:407:3: this_WriteCsv_2= ruleWriteCsv
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getWriteCsvParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_WriteCsv_2=ruleWriteCsv();

                    state._fsp--;


                    			current = this_WriteCsv_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalDsl.g:416:3: this_Transform_3= ruleTransform
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getTransformParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Transform_3=ruleTransform();

                    state._fsp--;


                    			current = this_Transform_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalDsl.g:425:3: this_GooglecalPUT_4= ruleGooglecalPUT
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getGooglecalPUTParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_GooglecalPUT_4=ruleGooglecalPUT();

                    state._fsp--;


                    			current = this_GooglecalPUT_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalDsl.g:434:3: this_SlackPUT_5= ruleSlackPUT
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getSlackPUTParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_SlackPUT_5=ruleSlackPUT();

                    state._fsp--;


                    			current = this_SlackPUT_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalDsl.g:443:3: this_ClickSendSms_6= ruleClickSendSms
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getClickSendSmsParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_ClickSendSms_6=ruleClickSendSms();

                    state._fsp--;


                    			current = this_ClickSendSms_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalDsl.g:452:3: this_Updatedaudit_7= ruleUpdatedaudit
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getUpdatedauditParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_Updatedaudit_7=ruleUpdatedaudit();

                    state._fsp--;


                    			current = this_Updatedaudit_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalDsl.g:461:3: this_Callprocess_8= ruleCallprocess
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getCallprocessParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_Callprocess_8=ruleCallprocess();

                    state._fsp--;


                    			current = this_Callprocess_8;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalDsl.g:470:3: this_Forkprocess_9= ruleForkprocess
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getForkprocessParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_Forkprocess_9=ruleForkprocess();

                    state._fsp--;


                    			current = this_Forkprocess_9;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalDsl.g:479:3: this_Fetch_10= ruleFetch
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getFetchParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_Fetch_10=ruleFetch();

                    state._fsp--;


                    			current = this_Fetch_10;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 12 :
                    // InternalDsl.g:488:3: this_TrelloPUT_11= ruleTrelloPUT
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getTrelloPUTParserRuleCall_11());
                    		
                    pushFollow(FOLLOW_2);
                    this_TrelloPUT_11=ruleTrelloPUT();

                    state._fsp--;


                    			current = this_TrelloPUT_11;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 13 :
                    // InternalDsl.g:497:3: this_TrelloGET_12= ruleTrelloGET
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getTrelloGETParserRuleCall_12());
                    		
                    pushFollow(FOLLOW_2);
                    this_TrelloGET_12=ruleTrelloGET();

                    state._fsp--;


                    			current = this_TrelloGET_12;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 14 :
                    // InternalDsl.g:506:3: this_Rest_13= ruleRest
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getRestParserRuleCall_13());
                    		
                    pushFollow(FOLLOW_2);
                    this_Rest_13=ruleRest();

                    state._fsp--;


                    			current = this_Rest_13;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 15 :
                    // InternalDsl.g:515:3: this_Doozle_14= ruleDoozle
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getDoozleParserRuleCall_14());
                    		
                    pushFollow(FOLLOW_2);
                    this_Doozle_14=ruleDoozle();

                    state._fsp--;


                    			current = this_Doozle_14;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 16 :
                    // InternalDsl.g:524:3: this_Dropfile_15= ruleDropfile
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getDropfileParserRuleCall_15());
                    		
                    pushFollow(FOLLOW_2);
                    this_Dropfile_15=ruleDropfile();

                    state._fsp--;


                    			current = this_Dropfile_15;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 17 :
                    // InternalDsl.g:533:3: this_FBCLead_16= ruleFBCLead
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getFBCLeadParserRuleCall_16());
                    		
                    pushFollow(FOLLOW_2);
                    this_FBCLead_16=ruleFBCLead();

                    state._fsp--;


                    			current = this_FBCLead_16;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 18 :
                    // InternalDsl.g:542:3: this_FBFormDownload_17= ruleFBFormDownload
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getFBFormDownloadParserRuleCall_17());
                    		
                    pushFollow(FOLLOW_2);
                    this_FBFormDownload_17=ruleFBFormDownload();

                    state._fsp--;


                    			current = this_FBFormDownload_17;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 19 :
                    // InternalDsl.g:551:3: this_SendMail_18= ruleSendMail
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getSendMailParserRuleCall_18());
                    		
                    pushFollow(FOLLOW_2);
                    this_SendMail_18=ruleSendMail();

                    state._fsp--;


                    			current = this_SendMail_18;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 20 :
                    // InternalDsl.g:560:3: this_GooglecontactPUT_19= ruleGooglecontactPUT
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getGooglecontactPUTParserRuleCall_19());
                    		
                    pushFollow(FOLLOW_2);
                    this_GooglecontactPUT_19=ruleGooglecontactPUT();

                    state._fsp--;


                    			current = this_GooglecontactPUT_19;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 21 :
                    // InternalDsl.g:569:3: this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getGooglecontactSelectAllParserRuleCall_20());
                    		
                    pushFollow(FOLLOW_2);
                    this_GooglecontactSelectAll_20=ruleGooglecontactSelectAll();

                    state._fsp--;


                    			current = this_GooglecontactSelectAll_20;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 22 :
                    // InternalDsl.g:578:3: this_Abort_21= ruleAbort
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getAbortParserRuleCall_21());
                    		
                    pushFollow(FOLLOW_2);
                    this_Abort_21=ruleAbort();

                    state._fsp--;


                    			current = this_Abort_21;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 23 :
                    // InternalDsl.g:587:3: this_SmsLeadSms_22= ruleSmsLeadSms
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getSmsLeadSmsParserRuleCall_22());
                    		
                    pushFollow(FOLLOW_2);
                    this_SmsLeadSms_22=ruleSmsLeadSms();

                    state._fsp--;


                    			current = this_SmsLeadSms_22;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 24 :
                    // InternalDsl.g:596:3: this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getFirebaseReactiveNotificationParserRuleCall_23());
                    		
                    pushFollow(FOLLOW_2);
                    this_FirebaseReactiveNotification_23=ruleFirebaseReactiveNotification();

                    state._fsp--;


                    			current = this_FirebaseReactiveNotification_23;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 25 :
                    // InternalDsl.g:605:3: this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getFirebaseDatabasePutParserRuleCall_24());
                    		
                    pushFollow(FOLLOW_2);
                    this_FirebaseDatabasePut_24=ruleFirebaseDatabasePut();

                    state._fsp--;


                    			current = this_FirebaseDatabasePut_24;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 26 :
                    // InternalDsl.g:614:3: this_ExecJava_25= ruleExecJava
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getExecJavaParserRuleCall_25());
                    		
                    pushFollow(FOLLOW_2);
                    this_ExecJava_25=ruleExecJava();

                    state._fsp--;


                    			current = this_ExecJava_25;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 27 :
                    // InternalDsl.g:623:3: this_ElasticFBCLead_26= ruleElasticFBCLead
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getElasticFBCLeadParserRuleCall_26());
                    		
                    pushFollow(FOLLOW_2);
                    this_ElasticFBCLead_26=ruleElasticFBCLead();

                    state._fsp--;


                    			current = this_ElasticFBCLead_26;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 28 :
                    // InternalDsl.g:632:3: this_ElasticGET_27= ruleElasticGET
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getElasticGETParserRuleCall_27());
                    		
                    pushFollow(FOLLOW_2);
                    this_ElasticGET_27=ruleElasticGET();

                    state._fsp--;


                    			current = this_ElasticGET_27;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 29 :
                    // InternalDsl.g:641:3: this_EsUpdate_28= ruleEsUpdate
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getEsUpdateParserRuleCall_28());
                    		
                    pushFollow(FOLLOW_2);
                    this_EsUpdate_28=ruleEsUpdate();

                    state._fsp--;


                    			current = this_EsUpdate_28;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 30 :
                    // InternalDsl.g:650:3: this_EsAccumulate_29= ruleEsAccumulate
                    {

                    			newCompositeNode(grammarAccess.getActionAccess().getEsAccumulateParserRuleCall_29());
                    		
                    pushFollow(FOLLOW_2);
                    this_EsAccumulate_29=ruleEsAccumulate();

                    state._fsp--;


                    			current = this_EsAccumulate_29;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAction"


    // $ANTLR start "entryRuleEsUpdate"
    // InternalDsl.g:662:1: entryRuleEsUpdate returns [EObject current=null] : iv_ruleEsUpdate= ruleEsUpdate EOF ;
    public final EObject entryRuleEsUpdate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEsUpdate = null;


        try {
            // InternalDsl.g:662:49: (iv_ruleEsUpdate= ruleEsUpdate EOF )
            // InternalDsl.g:663:2: iv_ruleEsUpdate= ruleEsUpdate EOF
            {
             newCompositeNode(grammarAccess.getEsUpdateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEsUpdate=ruleEsUpdate();

            state._fsp--;

             current =iv_ruleEsUpdate; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEsUpdate"


    // $ANTLR start "ruleEsUpdate"
    // InternalDsl.g:669:1: ruleEsUpdate returns [EObject current=null] : (otherlv_0= 'es-update' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= ruleNonSelectStatement ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) ) ;
    public final EObject ruleEsUpdate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_source_4_0=null;
        Token otherlv_5=null;
        Token lv_target_6_0=null;
        Token otherlv_7=null;
        Token lv_fetchSize_8_0=null;
        Token otherlv_9=null;
        Token lv_writeSize_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token lv_writeThreadCount_18_0=null;
        AntlrDatatypeRuleToken lv_value_13_0 = null;

        EObject lv_condition_16_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:675:2: ( (otherlv_0= 'es-update' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= ruleNonSelectStatement ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) ) )
            // InternalDsl.g:676:2: (otherlv_0= 'es-update' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= ruleNonSelectStatement ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:676:2: (otherlv_0= 'es-update' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= ruleNonSelectStatement ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) )
            // InternalDsl.g:677:3: otherlv_0= 'es-update' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= ruleNonSelectStatement ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getEsUpdateAccess().getEsUpdateKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getEsUpdateAccess().getAsKeyword_1());
            		
            // InternalDsl.g:685:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:686:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:686:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:687:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_name_2_0, grammarAccess.getEsUpdateAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsUpdateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getEsUpdateAccess().getFromKeyword_3());
            		
            // InternalDsl.g:707:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:708:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:708:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:709:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_source_4_0, grammarAccess.getEsUpdateAccess().getSourceSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsUpdateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getEsUpdateAccess().getToKeyword_5());
            		
            // InternalDsl.g:729:3: ( (lv_target_6_0= RULE_STRING ) )
            // InternalDsl.g:730:4: (lv_target_6_0= RULE_STRING )
            {
            // InternalDsl.g:730:4: (lv_target_6_0= RULE_STRING )
            // InternalDsl.g:731:5: lv_target_6_0= RULE_STRING
            {
            lv_target_6_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            					newLeafNode(lv_target_6_0, grammarAccess.getEsUpdateAccess().getTargetSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsUpdateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getEsUpdateAccess().getWithFetchsizeAsKeyword_7());
            		
            // InternalDsl.g:751:3: ( (lv_fetchSize_8_0= RULE_STRING ) )
            // InternalDsl.g:752:4: (lv_fetchSize_8_0= RULE_STRING )
            {
            // InternalDsl.g:752:4: (lv_fetchSize_8_0= RULE_STRING )
            // InternalDsl.g:753:5: lv_fetchSize_8_0= RULE_STRING
            {
            lv_fetchSize_8_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            					newLeafNode(lv_fetchSize_8_0, grammarAccess.getEsUpdateAccess().getFetchSizeSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsUpdateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"fetchSize",
            						lv_fetchSize_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,22,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getEsUpdateAccess().getAndWritesizeAsKeyword_9());
            		
            // InternalDsl.g:773:3: ( (lv_writeSize_10_0= RULE_STRING ) )
            // InternalDsl.g:774:4: (lv_writeSize_10_0= RULE_STRING )
            {
            // InternalDsl.g:774:4: (lv_writeSize_10_0= RULE_STRING )
            // InternalDsl.g:775:5: lv_writeSize_10_0= RULE_STRING
            {
            lv_writeSize_10_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_writeSize_10_0, grammarAccess.getEsUpdateAccess().getWriteSizeSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsUpdateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"writeSize",
            						lv_writeSize_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_11, grammarAccess.getEsUpdateAccess().getUsingKeyword_11());
            		
            otherlv_12=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_12, grammarAccess.getEsUpdateAccess().getLeftCurlyBracketKeyword_12());
            		
            // InternalDsl.g:799:3: ( (lv_value_13_0= ruleNonSelectStatement ) )
            // InternalDsl.g:800:4: (lv_value_13_0= ruleNonSelectStatement )
            {
            // InternalDsl.g:800:4: (lv_value_13_0= ruleNonSelectStatement )
            // InternalDsl.g:801:5: lv_value_13_0= ruleNonSelectStatement
            {

            					newCompositeNode(grammarAccess.getEsUpdateAccess().getValueNonSelectStatementParserRuleCall_13_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_13_0=ruleNonSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEsUpdateRule());
            					}
            					add(
            						current,
            						"value",
            						lv_value_13_0,
            						"in.handyman.Dsl.NonSelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_14=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_14, grammarAccess.getEsUpdateAccess().getRightCurlyBracketKeyword_14());
            		
            otherlv_15=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_15, grammarAccess.getEsUpdateAccess().getOnConditionKeyword_15());
            		
            // InternalDsl.g:826:3: ( (lv_condition_16_0= ruleExpression ) )
            // InternalDsl.g:827:4: (lv_condition_16_0= ruleExpression )
            {
            // InternalDsl.g:827:4: (lv_condition_16_0= ruleExpression )
            // InternalDsl.g:828:5: lv_condition_16_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getEsUpdateAccess().getConditionExpressionParserRuleCall_16_0());
            				
            pushFollow(FOLLOW_19);
            lv_condition_16_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEsUpdateRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_16_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_17=(Token)match(input,25,FOLLOW_3); 

            			newLeafNode(otherlv_17, grammarAccess.getEsUpdateAccess().getWithWriteThreadsKeyword_17());
            		
            // InternalDsl.g:849:3: ( (lv_writeThreadCount_18_0= RULE_STRING ) )
            // InternalDsl.g:850:4: (lv_writeThreadCount_18_0= RULE_STRING )
            {
            // InternalDsl.g:850:4: (lv_writeThreadCount_18_0= RULE_STRING )
            // InternalDsl.g:851:5: lv_writeThreadCount_18_0= RULE_STRING
            {
            lv_writeThreadCount_18_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_writeThreadCount_18_0, grammarAccess.getEsUpdateAccess().getWriteThreadCountSTRINGTerminalRuleCall_18_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsUpdateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"writeThreadCount",
            						lv_writeThreadCount_18_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEsUpdate"


    // $ANTLR start "entryRuleEsAccumulate"
    // InternalDsl.g:871:1: entryRuleEsAccumulate returns [EObject current=null] : iv_ruleEsAccumulate= ruleEsAccumulate EOF ;
    public final EObject entryRuleEsAccumulate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEsAccumulate = null;


        try {
            // InternalDsl.g:871:53: (iv_ruleEsAccumulate= ruleEsAccumulate EOF )
            // InternalDsl.g:872:2: iv_ruleEsAccumulate= ruleEsAccumulate EOF
            {
             newCompositeNode(grammarAccess.getEsAccumulateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEsAccumulate=ruleEsAccumulate();

            state._fsp--;

             current =iv_ruleEsAccumulate; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEsAccumulate"


    // $ANTLR start "ruleEsAccumulate"
    // InternalDsl.g:878:1: ruleEsAccumulate returns [EObject current=null] : (otherlv_0= 'es-accumulate' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= RULE_STRING ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) ) ;
    public final EObject ruleEsAccumulate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_source_4_0=null;
        Token otherlv_5=null;
        Token lv_target_6_0=null;
        Token otherlv_7=null;
        Token lv_fetchSize_8_0=null;
        Token otherlv_9=null;
        Token lv_writeSize_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token lv_value_13_0=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token lv_writeThreadCount_18_0=null;
        EObject lv_condition_16_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:884:2: ( (otherlv_0= 'es-accumulate' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= RULE_STRING ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) ) )
            // InternalDsl.g:885:2: (otherlv_0= 'es-accumulate' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= RULE_STRING ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:885:2: (otherlv_0= 'es-accumulate' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= RULE_STRING ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) ) )
            // InternalDsl.g:886:3: otherlv_0= 'es-accumulate' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_target_6_0= RULE_STRING ) ) otherlv_7= 'with-fetchsize-as' ( (lv_fetchSize_8_0= RULE_STRING ) ) otherlv_9= 'and-writesize-as' ( (lv_writeSize_10_0= RULE_STRING ) ) otherlv_11= 'using' otherlv_12= '{' ( (lv_value_13_0= RULE_STRING ) ) otherlv_14= '}' otherlv_15= 'on-condition' ( (lv_condition_16_0= ruleExpression ) ) otherlv_17= 'with-write-threads' ( (lv_writeThreadCount_18_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getEsAccumulateAccess().getEsAccumulateKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getEsAccumulateAccess().getAsKeyword_1());
            		
            // InternalDsl.g:894:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:895:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:895:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:896:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_name_2_0, grammarAccess.getEsAccumulateAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsAccumulateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getEsAccumulateAccess().getFromKeyword_3());
            		
            // InternalDsl.g:916:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:917:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:917:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:918:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_source_4_0, grammarAccess.getEsAccumulateAccess().getSourceSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsAccumulateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getEsAccumulateAccess().getToKeyword_5());
            		
            // InternalDsl.g:938:3: ( (lv_target_6_0= RULE_STRING ) )
            // InternalDsl.g:939:4: (lv_target_6_0= RULE_STRING )
            {
            // InternalDsl.g:939:4: (lv_target_6_0= RULE_STRING )
            // InternalDsl.g:940:5: lv_target_6_0= RULE_STRING
            {
            lv_target_6_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            					newLeafNode(lv_target_6_0, grammarAccess.getEsAccumulateAccess().getTargetSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsAccumulateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getEsAccumulateAccess().getWithFetchsizeAsKeyword_7());
            		
            // InternalDsl.g:960:3: ( (lv_fetchSize_8_0= RULE_STRING ) )
            // InternalDsl.g:961:4: (lv_fetchSize_8_0= RULE_STRING )
            {
            // InternalDsl.g:961:4: (lv_fetchSize_8_0= RULE_STRING )
            // InternalDsl.g:962:5: lv_fetchSize_8_0= RULE_STRING
            {
            lv_fetchSize_8_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            					newLeafNode(lv_fetchSize_8_0, grammarAccess.getEsAccumulateAccess().getFetchSizeSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsAccumulateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"fetchSize",
            						lv_fetchSize_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,22,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getEsAccumulateAccess().getAndWritesizeAsKeyword_9());
            		
            // InternalDsl.g:982:3: ( (lv_writeSize_10_0= RULE_STRING ) )
            // InternalDsl.g:983:4: (lv_writeSize_10_0= RULE_STRING )
            {
            // InternalDsl.g:983:4: (lv_writeSize_10_0= RULE_STRING )
            // InternalDsl.g:984:5: lv_writeSize_10_0= RULE_STRING
            {
            lv_writeSize_10_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_writeSize_10_0, grammarAccess.getEsAccumulateAccess().getWriteSizeSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsAccumulateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"writeSize",
            						lv_writeSize_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_11, grammarAccess.getEsAccumulateAccess().getUsingKeyword_11());
            		
            otherlv_12=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_12, grammarAccess.getEsAccumulateAccess().getLeftCurlyBracketKeyword_12());
            		
            // InternalDsl.g:1008:3: ( (lv_value_13_0= RULE_STRING ) )
            // InternalDsl.g:1009:4: (lv_value_13_0= RULE_STRING )
            {
            // InternalDsl.g:1009:4: (lv_value_13_0= RULE_STRING )
            // InternalDsl.g:1010:5: lv_value_13_0= RULE_STRING
            {
            lv_value_13_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_13_0, grammarAccess.getEsAccumulateAccess().getValueSTRINGTerminalRuleCall_13_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsAccumulateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_13_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_14=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_14, grammarAccess.getEsAccumulateAccess().getRightCurlyBracketKeyword_14());
            		
            otherlv_15=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_15, grammarAccess.getEsAccumulateAccess().getOnConditionKeyword_15());
            		
            // InternalDsl.g:1034:3: ( (lv_condition_16_0= ruleExpression ) )
            // InternalDsl.g:1035:4: (lv_condition_16_0= ruleExpression )
            {
            // InternalDsl.g:1035:4: (lv_condition_16_0= ruleExpression )
            // InternalDsl.g:1036:5: lv_condition_16_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getEsAccumulateAccess().getConditionExpressionParserRuleCall_16_0());
            				
            pushFollow(FOLLOW_19);
            lv_condition_16_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEsAccumulateRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_16_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_17=(Token)match(input,25,FOLLOW_3); 

            			newLeafNode(otherlv_17, grammarAccess.getEsAccumulateAccess().getWithWriteThreadsKeyword_17());
            		
            // InternalDsl.g:1057:3: ( (lv_writeThreadCount_18_0= RULE_STRING ) )
            // InternalDsl.g:1058:4: (lv_writeThreadCount_18_0= RULE_STRING )
            {
            // InternalDsl.g:1058:4: (lv_writeThreadCount_18_0= RULE_STRING )
            // InternalDsl.g:1059:5: lv_writeThreadCount_18_0= RULE_STRING
            {
            lv_writeThreadCount_18_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_writeThreadCount_18_0, grammarAccess.getEsAccumulateAccess().getWriteThreadCountSTRINGTerminalRuleCall_18_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEsAccumulateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"writeThreadCount",
            						lv_writeThreadCount_18_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEsAccumulate"


    // $ANTLR start "entryRuleElasticFBCLead"
    // InternalDsl.g:1079:1: entryRuleElasticFBCLead returns [EObject current=null] : iv_ruleElasticFBCLead= ruleElasticFBCLead EOF ;
    public final EObject entryRuleElasticFBCLead() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElasticFBCLead = null;


        try {
            // InternalDsl.g:1079:55: (iv_ruleElasticFBCLead= ruleElasticFBCLead EOF )
            // InternalDsl.g:1080:2: iv_ruleElasticFBCLead= ruleElasticFBCLead EOF
            {
             newCompositeNode(grammarAccess.getElasticFBCLeadRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElasticFBCLead=ruleElasticFBCLead();

            state._fsp--;

             current =iv_ruleElasticFBCLead; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElasticFBCLead"


    // $ANTLR start "ruleElasticFBCLead"
    // InternalDsl.g:1086:1: ruleElasticFBCLead returns [EObject current=null] : (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
    public final EObject ruleElasticFBCLead() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_accessToken_4_0=null;
        Token otherlv_5=null;
        Token lv_appSecret_6_0=null;
        Token otherlv_7=null;
        Token lv_accountId_8_0=null;
        Token otherlv_9=null;
        Token lv_campaignId_10_0=null;
        Token otherlv_11=null;
        Token lv_target_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_value_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        EObject lv_condition_18_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:1092:2: ( (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:1093:2: (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1093:2: (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:1094:3: otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,27,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getElasticFBCLeadAccess().getEfblcKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getElasticFBCLeadAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1102:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1103:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1103:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1104:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getElasticFBCLeadAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getElasticFBCLeadAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:1124:3: ( (lv_accessToken_4_0= RULE_STRING ) )
            // InternalDsl.g:1125:4: (lv_accessToken_4_0= RULE_STRING )
            {
            // InternalDsl.g:1125:4: (lv_accessToken_4_0= RULE_STRING )
            // InternalDsl.g:1126:5: lv_accessToken_4_0= RULE_STRING
            {
            lv_accessToken_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_accessToken_4_0, grammarAccess.getElasticFBCLeadAccess().getAccessTokenSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"accessToken",
            						lv_accessToken_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getElasticFBCLeadAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:1146:3: ( (lv_appSecret_6_0= RULE_STRING ) )
            // InternalDsl.g:1147:4: (lv_appSecret_6_0= RULE_STRING )
            {
            // InternalDsl.g:1147:4: (lv_appSecret_6_0= RULE_STRING )
            // InternalDsl.g:1148:5: lv_appSecret_6_0= RULE_STRING
            {
            lv_appSecret_6_0=(Token)match(input,RULE_STRING,FOLLOW_22); 

            					newLeafNode(lv_appSecret_6_0, grammarAccess.getElasticFBCLeadAccess().getAppSecretSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"appSecret",
            						lv_appSecret_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getElasticFBCLeadAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:1168:3: ( (lv_accountId_8_0= RULE_STRING ) )
            // InternalDsl.g:1169:4: (lv_accountId_8_0= RULE_STRING )
            {
            // InternalDsl.g:1169:4: (lv_accountId_8_0= RULE_STRING )
            // InternalDsl.g:1170:5: lv_accountId_8_0= RULE_STRING
            {
            lv_accountId_8_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

            					newLeafNode(lv_accountId_8_0, grammarAccess.getElasticFBCLeadAccess().getAccountIdSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"accountId",
            						lv_accountId_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,31,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getElasticFBCLeadAccess().getForCampaignKeyword_9());
            		
            // InternalDsl.g:1190:3: ( (lv_campaignId_10_0= RULE_STRING ) )
            // InternalDsl.g:1191:4: (lv_campaignId_10_0= RULE_STRING )
            {
            // InternalDsl.g:1191:4: (lv_campaignId_10_0= RULE_STRING )
            // InternalDsl.g:1192:5: lv_campaignId_10_0= RULE_STRING
            {
            lv_campaignId_10_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

            					newLeafNode(lv_campaignId_10_0, grammarAccess.getElasticFBCLeadAccess().getCampaignIdSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"campaignId",
            						lv_campaignId_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getElasticFBCLeadAccess().getIntoKeyword_11());
            		
            // InternalDsl.g:1212:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:1213:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:1213:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:1214:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_target_12_0, grammarAccess.getElasticFBCLeadAccess().getTargetSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getElasticFBCLeadAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getElasticFBCLeadAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:1238:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:1239:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:1239:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:1240:5: lv_value_15_0= RULE_STRING
            {
            lv_value_15_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_15_0, grammarAccess.getElasticFBCLeadAccess().getValueSTRINGTerminalRuleCall_15_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_15_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_16=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_16, grammarAccess.getElasticFBCLeadAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_17, grammarAccess.getElasticFBCLeadAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:1264:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:1265:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:1265:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:1266:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getElasticFBCLeadAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_18_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getElasticFBCLeadRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_18_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleElasticFBCLead"


    // $ANTLR start "entryRuleElasticGET"
    // InternalDsl.g:1287:1: entryRuleElasticGET returns [EObject current=null] : iv_ruleElasticGET= ruleElasticGET EOF ;
    public final EObject entryRuleElasticGET() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElasticGET = null;


        try {
            // InternalDsl.g:1287:51: (iv_ruleElasticGET= ruleElasticGET EOF )
            // InternalDsl.g:1288:2: iv_ruleElasticGET= ruleElasticGET EOF
            {
             newCompositeNode(grammarAccess.getElasticGETRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElasticGET=ruleElasticGET();

            state._fsp--;

             current =iv_ruleElasticGET; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElasticGET"


    // $ANTLR start "ruleElasticGET"
    // InternalDsl.g:1294:1: ruleElasticGET returns [EObject current=null] : (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
    public final EObject ruleElasticGET() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_source_4_0=null;
        Token otherlv_5=null;
        Token lv_to_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        AntlrDatatypeRuleToken lv_value_9_0 = null;

        EObject lv_condition_12_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:1300:2: ( (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:1301:2: (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1301:2: (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:1302:3: otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,33,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getElasticGETAccess().getElasticgetKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getElasticGETAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1310:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1311:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1311:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1312:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_name_2_0, grammarAccess.getElasticGETAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getElasticGETAccess().getFromKeyword_3());
            		
            // InternalDsl.g:1332:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:1333:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:1333:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:1334:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_source_4_0, grammarAccess.getElasticGETAccess().getSourceSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getElasticGETAccess().getToKeyword_5());
            		
            // InternalDsl.g:1354:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:1355:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:1355:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:1356:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_to_6_0, grammarAccess.getElasticGETAccess().getToSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElasticGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"to",
            						lv_to_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getElasticGETAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getElasticGETAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:1380:3: ( (lv_value_9_0= ruleSelectStatement ) )
            // InternalDsl.g:1381:4: (lv_value_9_0= ruleSelectStatement )
            {
            // InternalDsl.g:1381:4: (lv_value_9_0= ruleSelectStatement )
            // InternalDsl.g:1382:5: lv_value_9_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getElasticGETAccess().getValueSelectStatementParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_9_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getElasticGETRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_9_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_10, grammarAccess.getElasticGETAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_11, grammarAccess.getElasticGETAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:1407:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:1408:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:1408:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:1409:5: lv_condition_12_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getElasticGETAccess().getConditionExpressionParserRuleCall_12_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_12_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getElasticGETRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_12_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleElasticGET"


    // $ANTLR start "entryRuleExecJava"
    // InternalDsl.g:1430:1: entryRuleExecJava returns [EObject current=null] : iv_ruleExecJava= ruleExecJava EOF ;
    public final EObject entryRuleExecJava() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExecJava = null;


        try {
            // InternalDsl.g:1430:49: (iv_ruleExecJava= ruleExecJava EOF )
            // InternalDsl.g:1431:2: iv_ruleExecJava= ruleExecJava EOF
            {
             newCompositeNode(grammarAccess.getExecJavaRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExecJava=ruleExecJava();

            state._fsp--;

             current =iv_ruleExecJava; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExecJava"


    // $ANTLR start "ruleExecJava"
    // InternalDsl.g:1437:1: ruleExecJava returns [EObject current=null] : (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) ) ;
    public final EObject ruleExecJava() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_classFqn_4_0=null;
        Token otherlv_5=null;
        Token lv_dbSrc_6_0=null;
        Token otherlv_7=null;
        Token lv_value_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        EObject lv_condition_11_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:1443:2: ( (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) ) )
            // InternalDsl.g:1444:2: (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1444:2: (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) )
            // InternalDsl.g:1445:3: otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,34,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getExecJavaAccess().getJavaKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getExecJavaAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1453:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1454:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1454:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1455:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_name_2_0, grammarAccess.getExecJavaAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExecJavaRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getExecJavaAccess().getUsingKeyword_3());
            		
            // InternalDsl.g:1475:3: ( (lv_classFqn_4_0= RULE_STRING ) )
            // InternalDsl.g:1476:4: (lv_classFqn_4_0= RULE_STRING )
            {
            // InternalDsl.g:1476:4: (lv_classFqn_4_0= RULE_STRING )
            // InternalDsl.g:1477:5: lv_classFqn_4_0= RULE_STRING
            {
            lv_classFqn_4_0=(Token)match(input,RULE_STRING,FOLLOW_25); 

            					newLeafNode(lv_classFqn_4_0, grammarAccess.getExecJavaAccess().getClassFqnSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExecJavaRule());
            					}
            					setWithLastConsumed(
            						current,
            						"classFqn",
            						lv_classFqn_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getExecJavaAccess().getNameSakeDbKeyword_5());
            		
            // InternalDsl.g:1497:3: ( (lv_dbSrc_6_0= RULE_STRING ) )
            // InternalDsl.g:1498:4: (lv_dbSrc_6_0= RULE_STRING )
            {
            // InternalDsl.g:1498:4: (lv_dbSrc_6_0= RULE_STRING )
            // InternalDsl.g:1499:5: lv_dbSrc_6_0= RULE_STRING
            {
            lv_dbSrc_6_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					newLeafNode(lv_dbSrc_6_0, grammarAccess.getExecJavaAccess().getDbSrcSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExecJavaRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getExecJavaAccess().getLeftCurlyBracketKeyword_7());
            		
            // InternalDsl.g:1519:3: ( (lv_value_8_0= RULE_STRING ) )
            // InternalDsl.g:1520:4: (lv_value_8_0= RULE_STRING )
            {
            // InternalDsl.g:1520:4: (lv_value_8_0= RULE_STRING )
            // InternalDsl.g:1521:5: lv_value_8_0= RULE_STRING
            {
            lv_value_8_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_8_0, grammarAccess.getExecJavaAccess().getValueSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExecJavaRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_9, grammarAccess.getExecJavaAccess().getRightCurlyBracketKeyword_9());
            		
            otherlv_10=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_10, grammarAccess.getExecJavaAccess().getOnConditionKeyword_10());
            		
            // InternalDsl.g:1545:3: ( (lv_condition_11_0= ruleExpression ) )
            // InternalDsl.g:1546:4: (lv_condition_11_0= ruleExpression )
            {
            // InternalDsl.g:1546:4: (lv_condition_11_0= ruleExpression )
            // InternalDsl.g:1547:5: lv_condition_11_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getExecJavaAccess().getConditionExpressionParserRuleCall_11_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_11_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExecJavaRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_11_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExecJava"


    // $ANTLR start "entryRuleFirebaseDatabasePut"
    // InternalDsl.g:1568:1: entryRuleFirebaseDatabasePut returns [EObject current=null] : iv_ruleFirebaseDatabasePut= ruleFirebaseDatabasePut EOF ;
    public final EObject entryRuleFirebaseDatabasePut() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirebaseDatabasePut = null;


        try {
            // InternalDsl.g:1568:60: (iv_ruleFirebaseDatabasePut= ruleFirebaseDatabasePut EOF )
            // InternalDsl.g:1569:2: iv_ruleFirebaseDatabasePut= ruleFirebaseDatabasePut EOF
            {
             newCompositeNode(grammarAccess.getFirebaseDatabasePutRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFirebaseDatabasePut=ruleFirebaseDatabasePut();

            state._fsp--;

             current =iv_ruleFirebaseDatabasePut; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFirebaseDatabasePut"


    // $ANTLR start "ruleFirebaseDatabasePut"
    // InternalDsl.g:1575:1: ruleFirebaseDatabasePut returns [EObject current=null] : (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
    public final EObject ruleFirebaseDatabasePut() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_url_4_0=null;
        Token otherlv_5=null;
        Token lv_fbjson_6_0=null;
        Token otherlv_7=null;
        Token lv_groupPath_8_0=null;
        Token otherlv_9=null;
        Token lv_dbSrc_10_0=null;
        Token otherlv_11=null;
        Token lv_classFqn_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_value_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        EObject lv_condition_18_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:1581:2: ( (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:1582:2: (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1582:2: (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:1583:3: otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,36,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFirebaseDatabasePutAccess().getFbdpKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFirebaseDatabasePutAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1591:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1592:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1592:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1593:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

            					newLeafNode(lv_name_2_0, grammarAccess.getFirebaseDatabasePutAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,37,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFirebaseDatabasePutAccess().getOnKeyword_3());
            		
            // InternalDsl.g:1613:3: ( (lv_url_4_0= RULE_STRING ) )
            // InternalDsl.g:1614:4: (lv_url_4_0= RULE_STRING )
            {
            // InternalDsl.g:1614:4: (lv_url_4_0= RULE_STRING )
            // InternalDsl.g:1615:5: lv_url_4_0= RULE_STRING
            {
            lv_url_4_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

            					newLeafNode(lv_url_4_0, grammarAccess.getFirebaseDatabasePutAccess().getUrlSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					setWithLastConsumed(
            						current,
            						"url",
            						lv_url_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,38,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFirebaseDatabasePutAccess().getAuthByKeyword_5());
            		
            // InternalDsl.g:1635:3: ( (lv_fbjson_6_0= RULE_STRING ) )
            // InternalDsl.g:1636:4: (lv_fbjson_6_0= RULE_STRING )
            {
            // InternalDsl.g:1636:4: (lv_fbjson_6_0= RULE_STRING )
            // InternalDsl.g:1637:5: lv_fbjson_6_0= RULE_STRING
            {
            lv_fbjson_6_0=(Token)match(input,RULE_STRING,FOLLOW_28); 

            					newLeafNode(lv_fbjson_6_0, grammarAccess.getFirebaseDatabasePutAccess().getFbjsonSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					setWithLastConsumed(
            						current,
            						"fbjson",
            						lv_fbjson_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,39,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFirebaseDatabasePutAccess().getForGroupKeyword_7());
            		
            // InternalDsl.g:1657:3: ( (lv_groupPath_8_0= RULE_STRING ) )
            // InternalDsl.g:1658:4: (lv_groupPath_8_0= RULE_STRING )
            {
            // InternalDsl.g:1658:4: (lv_groupPath_8_0= RULE_STRING )
            // InternalDsl.g:1659:5: lv_groupPath_8_0= RULE_STRING
            {
            lv_groupPath_8_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_groupPath_8_0, grammarAccess.getFirebaseDatabasePutAccess().getGroupPathSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					setWithLastConsumed(
            						current,
            						"groupPath",
            						lv_groupPath_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFirebaseDatabasePutAccess().getFromSourceKeyword_9());
            		
            // InternalDsl.g:1679:3: ( (lv_dbSrc_10_0= RULE_STRING ) )
            // InternalDsl.g:1680:4: (lv_dbSrc_10_0= RULE_STRING )
            {
            // InternalDsl.g:1680:4: (lv_dbSrc_10_0= RULE_STRING )
            // InternalDsl.g:1681:5: lv_dbSrc_10_0= RULE_STRING
            {
            lv_dbSrc_10_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

            					newLeafNode(lv_dbSrc_10_0, grammarAccess.getFirebaseDatabasePutAccess().getDbSrcSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,41,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFirebaseDatabasePutAccess().getWithClassKeyword_11());
            		
            // InternalDsl.g:1701:3: ( (lv_classFqn_12_0= RULE_STRING ) )
            // InternalDsl.g:1702:4: (lv_classFqn_12_0= RULE_STRING )
            {
            // InternalDsl.g:1702:4: (lv_classFqn_12_0= RULE_STRING )
            // InternalDsl.g:1703:5: lv_classFqn_12_0= RULE_STRING
            {
            lv_classFqn_12_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_classFqn_12_0, grammarAccess.getFirebaseDatabasePutAccess().getClassFqnSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					setWithLastConsumed(
            						current,
            						"classFqn",
            						lv_classFqn_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getFirebaseDatabasePutAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getFirebaseDatabasePutAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:1727:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:1728:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:1728:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:1729:5: lv_value_15_0= RULE_STRING
            {
            lv_value_15_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_15_0, grammarAccess.getFirebaseDatabasePutAccess().getValueSTRINGTerminalRuleCall_15_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_15_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_16=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_16, grammarAccess.getFirebaseDatabasePutAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_17, grammarAccess.getFirebaseDatabasePutAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:1753:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:1754:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:1754:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:1755:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getFirebaseDatabasePutAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_18_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFirebaseDatabasePutRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_18_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFirebaseDatabasePut"


    // $ANTLR start "entryRuleFirebaseReactiveNotification"
    // InternalDsl.g:1776:1: entryRuleFirebaseReactiveNotification returns [EObject current=null] : iv_ruleFirebaseReactiveNotification= ruleFirebaseReactiveNotification EOF ;
    public final EObject entryRuleFirebaseReactiveNotification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirebaseReactiveNotification = null;


        try {
            // InternalDsl.g:1776:69: (iv_ruleFirebaseReactiveNotification= ruleFirebaseReactiveNotification EOF )
            // InternalDsl.g:1777:2: iv_ruleFirebaseReactiveNotification= ruleFirebaseReactiveNotification EOF
            {
             newCompositeNode(grammarAccess.getFirebaseReactiveNotificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFirebaseReactiveNotification=ruleFirebaseReactiveNotification();

            state._fsp--;

             current =iv_ruleFirebaseReactiveNotification; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFirebaseReactiveNotification"


    // $ANTLR start "ruleFirebaseReactiveNotification"
    // InternalDsl.g:1783:1: ruleFirebaseReactiveNotification returns [EObject current=null] : (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
    public final EObject ruleFirebaseReactiveNotification() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_url_4_0=null;
        Token otherlv_5=null;
        Token lv_fbjson_6_0=null;
        Token otherlv_7=null;
        Token lv_groupPath_8_0=null;
        Token otherlv_9=null;
        Token lv_classFqn_10_0=null;
        Token otherlv_11=null;
        Token lv_dbSrc_12_0=null;
        Token otherlv_13=null;
        EObject lv_condition_14_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:1789:2: ( (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:1790:2: (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1790:2: (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:1791:3: otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,42,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFirebaseReactiveNotificationAccess().getFbrnKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFirebaseReactiveNotificationAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1799:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1800:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1800:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1801:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

            					newLeafNode(lv_name_2_0, grammarAccess.getFirebaseReactiveNotificationAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseReactiveNotificationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,37,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFirebaseReactiveNotificationAccess().getOnKeyword_3());
            		
            // InternalDsl.g:1821:3: ( (lv_url_4_0= RULE_STRING ) )
            // InternalDsl.g:1822:4: (lv_url_4_0= RULE_STRING )
            {
            // InternalDsl.g:1822:4: (lv_url_4_0= RULE_STRING )
            // InternalDsl.g:1823:5: lv_url_4_0= RULE_STRING
            {
            lv_url_4_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

            					newLeafNode(lv_url_4_0, grammarAccess.getFirebaseReactiveNotificationAccess().getUrlSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseReactiveNotificationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"url",
            						lv_url_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,38,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFirebaseReactiveNotificationAccess().getAuthByKeyword_5());
            		
            // InternalDsl.g:1843:3: ( (lv_fbjson_6_0= RULE_STRING ) )
            // InternalDsl.g:1844:4: (lv_fbjson_6_0= RULE_STRING )
            {
            // InternalDsl.g:1844:4: (lv_fbjson_6_0= RULE_STRING )
            // InternalDsl.g:1845:5: lv_fbjson_6_0= RULE_STRING
            {
            lv_fbjson_6_0=(Token)match(input,RULE_STRING,FOLLOW_28); 

            					newLeafNode(lv_fbjson_6_0, grammarAccess.getFirebaseReactiveNotificationAccess().getFbjsonSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseReactiveNotificationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"fbjson",
            						lv_fbjson_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,39,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFirebaseReactiveNotificationAccess().getForGroupKeyword_7());
            		
            // InternalDsl.g:1865:3: ( (lv_groupPath_8_0= RULE_STRING ) )
            // InternalDsl.g:1866:4: (lv_groupPath_8_0= RULE_STRING )
            {
            // InternalDsl.g:1866:4: (lv_groupPath_8_0= RULE_STRING )
            // InternalDsl.g:1867:5: lv_groupPath_8_0= RULE_STRING
            {
            lv_groupPath_8_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

            					newLeafNode(lv_groupPath_8_0, grammarAccess.getFirebaseReactiveNotificationAccess().getGroupPathSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseReactiveNotificationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"groupPath",
            						lv_groupPath_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,41,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFirebaseReactiveNotificationAccess().getWithClassKeyword_9());
            		
            // InternalDsl.g:1887:3: ( (lv_classFqn_10_0= RULE_STRING ) )
            // InternalDsl.g:1888:4: (lv_classFqn_10_0= RULE_STRING )
            {
            // InternalDsl.g:1888:4: (lv_classFqn_10_0= RULE_STRING )
            // InternalDsl.g:1889:5: lv_classFqn_10_0= RULE_STRING
            {
            lv_classFqn_10_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_classFqn_10_0, grammarAccess.getFirebaseReactiveNotificationAccess().getClassFqnSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseReactiveNotificationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"classFqn",
            						lv_classFqn_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFirebaseReactiveNotificationAccess().getUsingKeyword_11());
            		
            // InternalDsl.g:1909:3: ( (lv_dbSrc_12_0= RULE_STRING ) )
            // InternalDsl.g:1910:4: (lv_dbSrc_12_0= RULE_STRING )
            {
            // InternalDsl.g:1910:4: (lv_dbSrc_12_0= RULE_STRING )
            // InternalDsl.g:1911:5: lv_dbSrc_12_0= RULE_STRING
            {
            lv_dbSrc_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

            					newLeafNode(lv_dbSrc_12_0, grammarAccess.getFirebaseReactiveNotificationAccess().getDbSrcSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFirebaseReactiveNotificationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_13, grammarAccess.getFirebaseReactiveNotificationAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:1931:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:1932:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:1932:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:1933:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getFirebaseReactiveNotificationAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_14_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFirebaseReactiveNotificationRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_14_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFirebaseReactiveNotification"


    // $ANTLR start "entryRuleSmsLeadSms"
    // InternalDsl.g:1954:1: entryRuleSmsLeadSms returns [EObject current=null] : iv_ruleSmsLeadSms= ruleSmsLeadSms EOF ;
    public final EObject entryRuleSmsLeadSms() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSmsLeadSms = null;


        try {
            // InternalDsl.g:1954:51: (iv_ruleSmsLeadSms= ruleSmsLeadSms EOF )
            // InternalDsl.g:1955:2: iv_ruleSmsLeadSms= ruleSmsLeadSms EOF
            {
             newCompositeNode(grammarAccess.getSmsLeadSmsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSmsLeadSms=ruleSmsLeadSms();

            state._fsp--;

             current =iv_ruleSmsLeadSms; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSmsLeadSms"


    // $ANTLR start "ruleSmsLeadSms"
    // InternalDsl.g:1961:1: ruleSmsLeadSms returns [EObject current=null] : (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) ) ;
    public final EObject ruleSmsLeadSms() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_url_4_0=null;
        Token otherlv_5=null;
        Token lv_sender_6_0=null;
        Token otherlv_7=null;
        Token lv_account_8_0=null;
        Token otherlv_9=null;
        Token lv_privateKey_10_0=null;
        Token otherlv_11=null;
        Token lv_dbSrc_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_value_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token lv_dryrunNumber_20_0=null;
        EObject lv_condition_18_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:1967:2: ( (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) ) )
            // InternalDsl.g:1968:2: (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:1968:2: (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) )
            // InternalDsl.g:1969:3: otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,43,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getSmsLeadSmsAccess().getSmsleadssmsKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getSmsLeadSmsAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1977:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1978:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1978:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1979:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

            					newLeafNode(lv_name_2_0, grammarAccess.getSmsLeadSmsAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,37,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getSmsLeadSmsAccess().getOnKeyword_3());
            		
            // InternalDsl.g:1999:3: ( (lv_url_4_0= RULE_STRING ) )
            // InternalDsl.g:2000:4: (lv_url_4_0= RULE_STRING )
            {
            // InternalDsl.g:2000:4: (lv_url_4_0= RULE_STRING )
            // InternalDsl.g:2001:5: lv_url_4_0= RULE_STRING
            {
            lv_url_4_0=(Token)match(input,RULE_STRING,FOLLOW_31); 

            					newLeafNode(lv_url_4_0, grammarAccess.getSmsLeadSmsAccess().getUrlSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"url",
            						lv_url_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,44,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getSmsLeadSmsAccess().getWithSenderKeyword_5());
            		
            // InternalDsl.g:2021:3: ( (lv_sender_6_0= RULE_STRING ) )
            // InternalDsl.g:2022:4: (lv_sender_6_0= RULE_STRING )
            {
            // InternalDsl.g:2022:4: (lv_sender_6_0= RULE_STRING )
            // InternalDsl.g:2023:5: lv_sender_6_0= RULE_STRING
            {
            lv_sender_6_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

            					newLeafNode(lv_sender_6_0, grammarAccess.getSmsLeadSmsAccess().getSenderSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"sender",
            						lv_sender_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getSmsLeadSmsAccess().getThroughAccountKeyword_7());
            		
            // InternalDsl.g:2043:3: ( (lv_account_8_0= RULE_STRING ) )
            // InternalDsl.g:2044:4: (lv_account_8_0= RULE_STRING )
            {
            // InternalDsl.g:2044:4: (lv_account_8_0= RULE_STRING )
            // InternalDsl.g:2045:5: lv_account_8_0= RULE_STRING
            {
            lv_account_8_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_account_8_0, grammarAccess.getSmsLeadSmsAccess().getAccountSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"account",
            						lv_account_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getSmsLeadSmsAccess().getSecuredByKeyword_9());
            		
            // InternalDsl.g:2065:3: ( (lv_privateKey_10_0= RULE_STRING ) )
            // InternalDsl.g:2066:4: (lv_privateKey_10_0= RULE_STRING )
            {
            // InternalDsl.g:2066:4: (lv_privateKey_10_0= RULE_STRING )
            // InternalDsl.g:2067:5: lv_privateKey_10_0= RULE_STRING
            {
            lv_privateKey_10_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_privateKey_10_0, grammarAccess.getSmsLeadSmsAccess().getPrivateKeySTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"privateKey",
            						lv_privateKey_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getSmsLeadSmsAccess().getFromSourceKeyword_11());
            		
            // InternalDsl.g:2087:3: ( (lv_dbSrc_12_0= RULE_STRING ) )
            // InternalDsl.g:2088:4: (lv_dbSrc_12_0= RULE_STRING )
            {
            // InternalDsl.g:2088:4: (lv_dbSrc_12_0= RULE_STRING )
            // InternalDsl.g:2089:5: lv_dbSrc_12_0= RULE_STRING
            {
            lv_dbSrc_12_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_dbSrc_12_0, grammarAccess.getSmsLeadSmsAccess().getDbSrcSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getSmsLeadSmsAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getSmsLeadSmsAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:2113:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:2114:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:2114:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:2115:5: lv_value_15_0= RULE_STRING
            {
            lv_value_15_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_15_0, grammarAccess.getSmsLeadSmsAccess().getValueSTRINGTerminalRuleCall_15_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_15_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_16=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_16, grammarAccess.getSmsLeadSmsAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_17, grammarAccess.getSmsLeadSmsAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:2139:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:2140:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:2140:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:2141:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getSmsLeadSmsAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_33);
            lv_condition_18_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSmsLeadSmsRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_18_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_19=(Token)match(input,46,FOLLOW_3); 

            			newLeafNode(otherlv_19, grammarAccess.getSmsLeadSmsAccess().getDoDryrunWithKeyword_19());
            		
            // InternalDsl.g:2162:3: ( (lv_dryrunNumber_20_0= RULE_STRING ) )
            // InternalDsl.g:2163:4: (lv_dryrunNumber_20_0= RULE_STRING )
            {
            // InternalDsl.g:2163:4: (lv_dryrunNumber_20_0= RULE_STRING )
            // InternalDsl.g:2164:5: lv_dryrunNumber_20_0= RULE_STRING
            {
            lv_dryrunNumber_20_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_dryrunNumber_20_0, grammarAccess.getSmsLeadSmsAccess().getDryrunNumberSTRINGTerminalRuleCall_20_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSmsLeadSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dryrunNumber",
            						lv_dryrunNumber_20_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSmsLeadSms"


    // $ANTLR start "entryRuleAbort"
    // InternalDsl.g:2184:1: entryRuleAbort returns [EObject current=null] : iv_ruleAbort= ruleAbort EOF ;
    public final EObject entryRuleAbort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAbort = null;


        try {
            // InternalDsl.g:2184:46: (iv_ruleAbort= ruleAbort EOF )
            // InternalDsl.g:2185:2: iv_ruleAbort= ruleAbort EOF
            {
             newCompositeNode(grammarAccess.getAbortRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAbort=ruleAbort();

            state._fsp--;

             current =iv_ruleAbort; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbort"


    // $ANTLR start "ruleAbort"
    // InternalDsl.g:2191:1: ruleAbort returns [EObject current=null] : (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) ) ;
    public final EObject ruleAbort() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_value_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_condition_7_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:2197:2: ( (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) ) )
            // InternalDsl.g:2198:2: (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2198:2: (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) )
            // InternalDsl.g:2199:3: otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,47,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getAbortAccess().getAbortKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getAbortAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2207:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2208:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2208:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2209:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getAbortAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAbortRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getAbortAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalDsl.g:2229:3: ( (lv_value_4_0= RULE_STRING ) )
            // InternalDsl.g:2230:4: (lv_value_4_0= RULE_STRING )
            {
            // InternalDsl.g:2230:4: (lv_value_4_0= RULE_STRING )
            // InternalDsl.g:2231:5: lv_value_4_0= RULE_STRING
            {
            lv_value_4_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_4_0, grammarAccess.getAbortAccess().getValueSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAbortRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_5, grammarAccess.getAbortAccess().getRightCurlyBracketKeyword_5());
            		
            otherlv_6=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_6, grammarAccess.getAbortAccess().getOnConditionKeyword_6());
            		
            // InternalDsl.g:2255:3: ( (lv_condition_7_0= ruleExpression ) )
            // InternalDsl.g:2256:4: (lv_condition_7_0= ruleExpression )
            {
            // InternalDsl.g:2256:4: (lv_condition_7_0= ruleExpression )
            // InternalDsl.g:2257:5: lv_condition_7_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getAbortAccess().getConditionExpressionParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_7_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAbortRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_7_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbort"


    // $ANTLR start "entryRuleGooglecontactSelectAll"
    // InternalDsl.g:2278:1: entryRuleGooglecontactSelectAll returns [EObject current=null] : iv_ruleGooglecontactSelectAll= ruleGooglecontactSelectAll EOF ;
    public final EObject entryRuleGooglecontactSelectAll() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGooglecontactSelectAll = null;


        try {
            // InternalDsl.g:2278:63: (iv_ruleGooglecontactSelectAll= ruleGooglecontactSelectAll EOF )
            // InternalDsl.g:2279:2: iv_ruleGooglecontactSelectAll= ruleGooglecontactSelectAll EOF
            {
             newCompositeNode(grammarAccess.getGooglecontactSelectAllRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGooglecontactSelectAll=ruleGooglecontactSelectAll();

            state._fsp--;

             current =iv_ruleGooglecontactSelectAll; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGooglecontactSelectAll"


    // $ANTLR start "ruleGooglecontactSelectAll"
    // InternalDsl.g:2285:1: ruleGooglecontactSelectAll returns [EObject current=null] : (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) ;
    public final EObject ruleGooglecontactSelectAll() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_account_4_0=null;
        Token otherlv_5=null;
        Token lv_privateKey_6_0=null;
        Token otherlv_7=null;
        Token lv_ptwelveFile_8_0=null;
        Token otherlv_9=null;
        Token lv_project_10_0=null;
        Token otherlv_11=null;
        Token lv_impersonatedUser_12_0=null;
        Token otherlv_13=null;
        Token lv_dbSrc_14_0=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token lv_value_17_0=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        EObject lv_condition_20_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:2291:2: ( (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) )
            // InternalDsl.g:2292:2: (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2292:2: (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            // InternalDsl.g:2293:3: otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,48,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getGooglecontactSelectAllAccess().getGcontactFetchallKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGooglecontactSelectAllAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2301:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2302:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2302:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2303:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

            					newLeafNode(lv_name_2_0, grammarAccess.getGooglecontactSelectAllAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getGooglecontactSelectAllAccess().getThroughAccountKeyword_3());
            		
            // InternalDsl.g:2323:3: ( (lv_account_4_0= RULE_STRING ) )
            // InternalDsl.g:2324:4: (lv_account_4_0= RULE_STRING )
            {
            // InternalDsl.g:2324:4: (lv_account_4_0= RULE_STRING )
            // InternalDsl.g:2325:5: lv_account_4_0= RULE_STRING
            {
            lv_account_4_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_account_4_0, grammarAccess.getGooglecontactSelectAllAccess().getAccountSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"account",
            						lv_account_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getGooglecontactSelectAllAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:2345:3: ( (lv_privateKey_6_0= RULE_STRING ) )
            // InternalDsl.g:2346:4: (lv_privateKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:2346:4: (lv_privateKey_6_0= RULE_STRING )
            // InternalDsl.g:2347:5: lv_privateKey_6_0= RULE_STRING
            {
            lv_privateKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_privateKey_6_0, grammarAccess.getGooglecontactSelectAllAccess().getPrivateKeySTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"privateKey",
            						lv_privateKey_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGooglecontactSelectAllAccess().getWithKeyKeyword_7());
            		
            // InternalDsl.g:2367:3: ( (lv_ptwelveFile_8_0= RULE_STRING ) )
            // InternalDsl.g:2368:4: (lv_ptwelveFile_8_0= RULE_STRING )
            {
            // InternalDsl.g:2368:4: (lv_ptwelveFile_8_0= RULE_STRING )
            // InternalDsl.g:2369:5: lv_ptwelveFile_8_0= RULE_STRING
            {
            lv_ptwelveFile_8_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

            					newLeafNode(lv_ptwelveFile_8_0, grammarAccess.getGooglecontactSelectAllAccess().getPtwelveFileSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"ptwelveFile",
            						lv_ptwelveFile_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,49,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getGooglecontactSelectAllAccess().getForProjectKeyword_9());
            		
            // InternalDsl.g:2389:3: ( (lv_project_10_0= RULE_STRING ) )
            // InternalDsl.g:2390:4: (lv_project_10_0= RULE_STRING )
            {
            // InternalDsl.g:2390:4: (lv_project_10_0= RULE_STRING )
            // InternalDsl.g:2391:5: lv_project_10_0= RULE_STRING
            {
            lv_project_10_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            					newLeafNode(lv_project_10_0, grammarAccess.getGooglecontactSelectAllAccess().getProjectSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"project",
            						lv_project_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,50,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getGooglecontactSelectAllAccess().getOnBehalfOfKeyword_11());
            		
            // InternalDsl.g:2411:3: ( (lv_impersonatedUser_12_0= RULE_STRING ) )
            // InternalDsl.g:2412:4: (lv_impersonatedUser_12_0= RULE_STRING )
            {
            // InternalDsl.g:2412:4: (lv_impersonatedUser_12_0= RULE_STRING )
            // InternalDsl.g:2413:5: lv_impersonatedUser_12_0= RULE_STRING
            {
            lv_impersonatedUser_12_0=(Token)match(input,RULE_STRING,FOLLOW_36); 

            					newLeafNode(lv_impersonatedUser_12_0, grammarAccess.getGooglecontactSelectAllAccess().getImpersonatedUserSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"impersonatedUser",
            						lv_impersonatedUser_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,51,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getGooglecontactSelectAllAccess().getToTargetKeyword_13());
            		
            // InternalDsl.g:2433:3: ( (lv_dbSrc_14_0= RULE_STRING ) )
            // InternalDsl.g:2434:4: (lv_dbSrc_14_0= RULE_STRING )
            {
            // InternalDsl.g:2434:4: (lv_dbSrc_14_0= RULE_STRING )
            // InternalDsl.g:2435:5: lv_dbSrc_14_0= RULE_STRING
            {
            lv_dbSrc_14_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_dbSrc_14_0, grammarAccess.getGooglecontactSelectAllAccess().getDbSrcSTRINGTerminalRuleCall_14_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_14_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_15=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_15, grammarAccess.getGooglecontactSelectAllAccess().getUsingKeyword_15());
            		
            otherlv_16=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getGooglecontactSelectAllAccess().getLeftCurlyBracketKeyword_16());
            		
            // InternalDsl.g:2459:3: ( (lv_value_17_0= RULE_STRING ) )
            // InternalDsl.g:2460:4: (lv_value_17_0= RULE_STRING )
            {
            // InternalDsl.g:2460:4: (lv_value_17_0= RULE_STRING )
            // InternalDsl.g:2461:5: lv_value_17_0= RULE_STRING
            {
            lv_value_17_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_17_0, grammarAccess.getGooglecontactSelectAllAccess().getValueSTRINGTerminalRuleCall_17_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_17_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_18=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_18, grammarAccess.getGooglecontactSelectAllAccess().getRightCurlyBracketKeyword_18());
            		
            otherlv_19=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_19, grammarAccess.getGooglecontactSelectAllAccess().getOnConditionKeyword_19());
            		
            // InternalDsl.g:2485:3: ( (lv_condition_20_0= ruleExpression ) )
            // InternalDsl.g:2486:4: (lv_condition_20_0= ruleExpression )
            {
            // InternalDsl.g:2486:4: (lv_condition_20_0= ruleExpression )
            // InternalDsl.g:2487:5: lv_condition_20_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getGooglecontactSelectAllAccess().getConditionExpressionParserRuleCall_20_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_20_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGooglecontactSelectAllRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_20_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGooglecontactSelectAll"


    // $ANTLR start "entryRuleSendMail"
    // InternalDsl.g:2508:1: entryRuleSendMail returns [EObject current=null] : iv_ruleSendMail= ruleSendMail EOF ;
    public final EObject entryRuleSendMail() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSendMail = null;


        try {
            // InternalDsl.g:2508:49: (iv_ruleSendMail= ruleSendMail EOF )
            // InternalDsl.g:2509:2: iv_ruleSendMail= ruleSendMail EOF
            {
             newCompositeNode(grammarAccess.getSendMailRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSendMail=ruleSendMail();

            state._fsp--;

             current =iv_ruleSendMail; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSendMail"


    // $ANTLR start "ruleSendMail"
    // InternalDsl.g:2515:1: ruleSendMail returns [EObject current=null] : (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) ) ;
    public final EObject ruleSendMail() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_privateKey_4_0=null;
        Token otherlv_5=null;
        Token lv_impersonatedUser_6_0=null;
        Token otherlv_7=null;
        Token lv_dbSrc_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token lv_value_11_0=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token lv_dryrunMail_16_0=null;
        EObject lv_condition_14_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:2521:2: ( (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) ) )
            // InternalDsl.g:2522:2: (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:2522:2: (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) )
            // InternalDsl.g:2523:3: otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,52,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getSendMailAccess().getSendmailKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getSendMailAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2531:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2532:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2532:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2533:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getSendMailAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSendMailRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getSendMailAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:2553:3: ( (lv_privateKey_4_0= RULE_STRING ) )
            // InternalDsl.g:2554:4: (lv_privateKey_4_0= RULE_STRING )
            {
            // InternalDsl.g:2554:4: (lv_privateKey_4_0= RULE_STRING )
            // InternalDsl.g:2555:5: lv_privateKey_4_0= RULE_STRING
            {
            lv_privateKey_4_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            					newLeafNode(lv_privateKey_4_0, grammarAccess.getSendMailAccess().getPrivateKeySTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSendMailRule());
            					}
            					setWithLastConsumed(
            						current,
            						"privateKey",
            						lv_privateKey_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,50,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getSendMailAccess().getOnBehalfOfKeyword_5());
            		
            // InternalDsl.g:2575:3: ( (lv_impersonatedUser_6_0= RULE_STRING ) )
            // InternalDsl.g:2576:4: (lv_impersonatedUser_6_0= RULE_STRING )
            {
            // InternalDsl.g:2576:4: (lv_impersonatedUser_6_0= RULE_STRING )
            // InternalDsl.g:2577:5: lv_impersonatedUser_6_0= RULE_STRING
            {
            lv_impersonatedUser_6_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_impersonatedUser_6_0, grammarAccess.getSendMailAccess().getImpersonatedUserSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSendMailRule());
            					}
            					setWithLastConsumed(
            						current,
            						"impersonatedUser",
            						lv_impersonatedUser_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getSendMailAccess().getFromSourceKeyword_7());
            		
            // InternalDsl.g:2597:3: ( (lv_dbSrc_8_0= RULE_STRING ) )
            // InternalDsl.g:2598:4: (lv_dbSrc_8_0= RULE_STRING )
            {
            // InternalDsl.g:2598:4: (lv_dbSrc_8_0= RULE_STRING )
            // InternalDsl.g:2599:5: lv_dbSrc_8_0= RULE_STRING
            {
            lv_dbSrc_8_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_dbSrc_8_0, grammarAccess.getSendMailAccess().getDbSrcSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSendMailRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getSendMailAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getSendMailAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:2623:3: ( (lv_value_11_0= RULE_STRING ) )
            // InternalDsl.g:2624:4: (lv_value_11_0= RULE_STRING )
            {
            // InternalDsl.g:2624:4: (lv_value_11_0= RULE_STRING )
            // InternalDsl.g:2625:5: lv_value_11_0= RULE_STRING
            {
            lv_value_11_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_11_0, grammarAccess.getSendMailAccess().getValueSTRINGTerminalRuleCall_11_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSendMailRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_11_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_12=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_12, grammarAccess.getSendMailAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_13, grammarAccess.getSendMailAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:2649:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:2650:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:2650:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:2651:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getSendMailAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_33);
            lv_condition_14_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSendMailRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_14_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_15=(Token)match(input,46,FOLLOW_3); 

            			newLeafNode(otherlv_15, grammarAccess.getSendMailAccess().getDoDryrunWithKeyword_15());
            		
            // InternalDsl.g:2672:3: ( (lv_dryrunMail_16_0= RULE_STRING ) )
            // InternalDsl.g:2673:4: (lv_dryrunMail_16_0= RULE_STRING )
            {
            // InternalDsl.g:2673:4: (lv_dryrunMail_16_0= RULE_STRING )
            // InternalDsl.g:2674:5: lv_dryrunMail_16_0= RULE_STRING
            {
            lv_dryrunMail_16_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_dryrunMail_16_0, grammarAccess.getSendMailAccess().getDryrunMailSTRINGTerminalRuleCall_16_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSendMailRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dryrunMail",
            						lv_dryrunMail_16_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSendMail"


    // $ANTLR start "entryRuleGooglecontactPUT"
    // InternalDsl.g:2694:1: entryRuleGooglecontactPUT returns [EObject current=null] : iv_ruleGooglecontactPUT= ruleGooglecontactPUT EOF ;
    public final EObject entryRuleGooglecontactPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGooglecontactPUT = null;


        try {
            // InternalDsl.g:2694:57: (iv_ruleGooglecontactPUT= ruleGooglecontactPUT EOF )
            // InternalDsl.g:2695:2: iv_ruleGooglecontactPUT= ruleGooglecontactPUT EOF
            {
             newCompositeNode(grammarAccess.getGooglecontactPUTRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGooglecontactPUT=ruleGooglecontactPUT();

            state._fsp--;

             current =iv_ruleGooglecontactPUT; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGooglecontactPUT"


    // $ANTLR start "ruleGooglecontactPUT"
    // InternalDsl.g:2701:1: ruleGooglecontactPUT returns [EObject current=null] : (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) ;
    public final EObject ruleGooglecontactPUT() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_account_4_0=null;
        Token otherlv_5=null;
        Token lv_privateKey_6_0=null;
        Token otherlv_7=null;
        Token lv_ptwelveFile_8_0=null;
        Token otherlv_9=null;
        Token lv_project_10_0=null;
        Token otherlv_11=null;
        Token lv_impersonatedUser_12_0=null;
        Token otherlv_13=null;
        Token lv_dbSrc_14_0=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token lv_value_17_0=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        EObject lv_condition_20_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:2707:2: ( (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) )
            // InternalDsl.g:2708:2: (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2708:2: (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            // InternalDsl.g:2709:3: otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,53,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getGooglecontactPUTAccess().getGcontactKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGooglecontactPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2717:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2718:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2718:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2719:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

            					newLeafNode(lv_name_2_0, grammarAccess.getGooglecontactPUTAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getGooglecontactPUTAccess().getThroughAccountKeyword_3());
            		
            // InternalDsl.g:2739:3: ( (lv_account_4_0= RULE_STRING ) )
            // InternalDsl.g:2740:4: (lv_account_4_0= RULE_STRING )
            {
            // InternalDsl.g:2740:4: (lv_account_4_0= RULE_STRING )
            // InternalDsl.g:2741:5: lv_account_4_0= RULE_STRING
            {
            lv_account_4_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_account_4_0, grammarAccess.getGooglecontactPUTAccess().getAccountSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"account",
            						lv_account_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getGooglecontactPUTAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:2761:3: ( (lv_privateKey_6_0= RULE_STRING ) )
            // InternalDsl.g:2762:4: (lv_privateKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:2762:4: (lv_privateKey_6_0= RULE_STRING )
            // InternalDsl.g:2763:5: lv_privateKey_6_0= RULE_STRING
            {
            lv_privateKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_privateKey_6_0, grammarAccess.getGooglecontactPUTAccess().getPrivateKeySTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"privateKey",
            						lv_privateKey_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGooglecontactPUTAccess().getWithKeyKeyword_7());
            		
            // InternalDsl.g:2783:3: ( (lv_ptwelveFile_8_0= RULE_STRING ) )
            // InternalDsl.g:2784:4: (lv_ptwelveFile_8_0= RULE_STRING )
            {
            // InternalDsl.g:2784:4: (lv_ptwelveFile_8_0= RULE_STRING )
            // InternalDsl.g:2785:5: lv_ptwelveFile_8_0= RULE_STRING
            {
            lv_ptwelveFile_8_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

            					newLeafNode(lv_ptwelveFile_8_0, grammarAccess.getGooglecontactPUTAccess().getPtwelveFileSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"ptwelveFile",
            						lv_ptwelveFile_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,49,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getGooglecontactPUTAccess().getForProjectKeyword_9());
            		
            // InternalDsl.g:2805:3: ( (lv_project_10_0= RULE_STRING ) )
            // InternalDsl.g:2806:4: (lv_project_10_0= RULE_STRING )
            {
            // InternalDsl.g:2806:4: (lv_project_10_0= RULE_STRING )
            // InternalDsl.g:2807:5: lv_project_10_0= RULE_STRING
            {
            lv_project_10_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            					newLeafNode(lv_project_10_0, grammarAccess.getGooglecontactPUTAccess().getProjectSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"project",
            						lv_project_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,50,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getGooglecontactPUTAccess().getOnBehalfOfKeyword_11());
            		
            // InternalDsl.g:2827:3: ( (lv_impersonatedUser_12_0= RULE_STRING ) )
            // InternalDsl.g:2828:4: (lv_impersonatedUser_12_0= RULE_STRING )
            {
            // InternalDsl.g:2828:4: (lv_impersonatedUser_12_0= RULE_STRING )
            // InternalDsl.g:2829:5: lv_impersonatedUser_12_0= RULE_STRING
            {
            lv_impersonatedUser_12_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_impersonatedUser_12_0, grammarAccess.getGooglecontactPUTAccess().getImpersonatedUserSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"impersonatedUser",
            						lv_impersonatedUser_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getGooglecontactPUTAccess().getFromSourceKeyword_13());
            		
            // InternalDsl.g:2849:3: ( (lv_dbSrc_14_0= RULE_STRING ) )
            // InternalDsl.g:2850:4: (lv_dbSrc_14_0= RULE_STRING )
            {
            // InternalDsl.g:2850:4: (lv_dbSrc_14_0= RULE_STRING )
            // InternalDsl.g:2851:5: lv_dbSrc_14_0= RULE_STRING
            {
            lv_dbSrc_14_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_dbSrc_14_0, grammarAccess.getGooglecontactPUTAccess().getDbSrcSTRINGTerminalRuleCall_14_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_14_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_15=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_15, grammarAccess.getGooglecontactPUTAccess().getUsingKeyword_15());
            		
            otherlv_16=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getGooglecontactPUTAccess().getLeftCurlyBracketKeyword_16());
            		
            // InternalDsl.g:2875:3: ( (lv_value_17_0= RULE_STRING ) )
            // InternalDsl.g:2876:4: (lv_value_17_0= RULE_STRING )
            {
            // InternalDsl.g:2876:4: (lv_value_17_0= RULE_STRING )
            // InternalDsl.g:2877:5: lv_value_17_0= RULE_STRING
            {
            lv_value_17_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_17_0, grammarAccess.getGooglecontactPUTAccess().getValueSTRINGTerminalRuleCall_17_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecontactPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_17_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_18=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_18, grammarAccess.getGooglecontactPUTAccess().getRightCurlyBracketKeyword_18());
            		
            otherlv_19=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_19, grammarAccess.getGooglecontactPUTAccess().getOnConditionKeyword_19());
            		
            // InternalDsl.g:2901:3: ( (lv_condition_20_0= ruleExpression ) )
            // InternalDsl.g:2902:4: (lv_condition_20_0= ruleExpression )
            {
            // InternalDsl.g:2902:4: (lv_condition_20_0= ruleExpression )
            // InternalDsl.g:2903:5: lv_condition_20_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getGooglecontactPUTAccess().getConditionExpressionParserRuleCall_20_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_20_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGooglecontactPUTRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_20_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGooglecontactPUT"


    // $ANTLR start "entryRuleGooglecalPUT"
    // InternalDsl.g:2924:1: entryRuleGooglecalPUT returns [EObject current=null] : iv_ruleGooglecalPUT= ruleGooglecalPUT EOF ;
    public final EObject entryRuleGooglecalPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGooglecalPUT = null;


        try {
            // InternalDsl.g:2924:53: (iv_ruleGooglecalPUT= ruleGooglecalPUT EOF )
            // InternalDsl.g:2925:2: iv_ruleGooglecalPUT= ruleGooglecalPUT EOF
            {
             newCompositeNode(grammarAccess.getGooglecalPUTRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGooglecalPUT=ruleGooglecalPUT();

            state._fsp--;

             current =iv_ruleGooglecalPUT; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGooglecalPUT"


    // $ANTLR start "ruleGooglecalPUT"
    // InternalDsl.g:2931:1: ruleGooglecalPUT returns [EObject current=null] : (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) ;
    public final EObject ruleGooglecalPUT() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_account_4_0=null;
        Token otherlv_5=null;
        Token lv_privateKey_6_0=null;
        Token otherlv_7=null;
        Token lv_ptwelveFile_8_0=null;
        Token otherlv_9=null;
        Token lv_project_10_0=null;
        Token otherlv_11=null;
        Token lv_impersonatedUser_12_0=null;
        Token otherlv_13=null;
        Token lv_dbSrc_14_0=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token lv_value_17_0=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        EObject lv_condition_20_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:2937:2: ( (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) )
            // InternalDsl.g:2938:2: (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2938:2: (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            // InternalDsl.g:2939:3: otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,54,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getGooglecalPUTAccess().getGcalendarKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGooglecalPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2947:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2948:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2948:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2949:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

            					newLeafNode(lv_name_2_0, grammarAccess.getGooglecalPUTAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getGooglecalPUTAccess().getThroughAccountKeyword_3());
            		
            // InternalDsl.g:2969:3: ( (lv_account_4_0= RULE_STRING ) )
            // InternalDsl.g:2970:4: (lv_account_4_0= RULE_STRING )
            {
            // InternalDsl.g:2970:4: (lv_account_4_0= RULE_STRING )
            // InternalDsl.g:2971:5: lv_account_4_0= RULE_STRING
            {
            lv_account_4_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_account_4_0, grammarAccess.getGooglecalPUTAccess().getAccountSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"account",
            						lv_account_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getGooglecalPUTAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:2991:3: ( (lv_privateKey_6_0= RULE_STRING ) )
            // InternalDsl.g:2992:4: (lv_privateKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:2992:4: (lv_privateKey_6_0= RULE_STRING )
            // InternalDsl.g:2993:5: lv_privateKey_6_0= RULE_STRING
            {
            lv_privateKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_privateKey_6_0, grammarAccess.getGooglecalPUTAccess().getPrivateKeySTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"privateKey",
            						lv_privateKey_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGooglecalPUTAccess().getWithKeyKeyword_7());
            		
            // InternalDsl.g:3013:3: ( (lv_ptwelveFile_8_0= RULE_STRING ) )
            // InternalDsl.g:3014:4: (lv_ptwelveFile_8_0= RULE_STRING )
            {
            // InternalDsl.g:3014:4: (lv_ptwelveFile_8_0= RULE_STRING )
            // InternalDsl.g:3015:5: lv_ptwelveFile_8_0= RULE_STRING
            {
            lv_ptwelveFile_8_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

            					newLeafNode(lv_ptwelveFile_8_0, grammarAccess.getGooglecalPUTAccess().getPtwelveFileSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"ptwelveFile",
            						lv_ptwelveFile_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,49,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getGooglecalPUTAccess().getForProjectKeyword_9());
            		
            // InternalDsl.g:3035:3: ( (lv_project_10_0= RULE_STRING ) )
            // InternalDsl.g:3036:4: (lv_project_10_0= RULE_STRING )
            {
            // InternalDsl.g:3036:4: (lv_project_10_0= RULE_STRING )
            // InternalDsl.g:3037:5: lv_project_10_0= RULE_STRING
            {
            lv_project_10_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            					newLeafNode(lv_project_10_0, grammarAccess.getGooglecalPUTAccess().getProjectSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"project",
            						lv_project_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,50,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getGooglecalPUTAccess().getOnBehalfOfKeyword_11());
            		
            // InternalDsl.g:3057:3: ( (lv_impersonatedUser_12_0= RULE_STRING ) )
            // InternalDsl.g:3058:4: (lv_impersonatedUser_12_0= RULE_STRING )
            {
            // InternalDsl.g:3058:4: (lv_impersonatedUser_12_0= RULE_STRING )
            // InternalDsl.g:3059:5: lv_impersonatedUser_12_0= RULE_STRING
            {
            lv_impersonatedUser_12_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_impersonatedUser_12_0, grammarAccess.getGooglecalPUTAccess().getImpersonatedUserSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"impersonatedUser",
            						lv_impersonatedUser_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getGooglecalPUTAccess().getFromSourceKeyword_13());
            		
            // InternalDsl.g:3079:3: ( (lv_dbSrc_14_0= RULE_STRING ) )
            // InternalDsl.g:3080:4: (lv_dbSrc_14_0= RULE_STRING )
            {
            // InternalDsl.g:3080:4: (lv_dbSrc_14_0= RULE_STRING )
            // InternalDsl.g:3081:5: lv_dbSrc_14_0= RULE_STRING
            {
            lv_dbSrc_14_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_dbSrc_14_0, grammarAccess.getGooglecalPUTAccess().getDbSrcSTRINGTerminalRuleCall_14_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"dbSrc",
            						lv_dbSrc_14_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_15=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_15, grammarAccess.getGooglecalPUTAccess().getUsingKeyword_15());
            		
            otherlv_16=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getGooglecalPUTAccess().getLeftCurlyBracketKeyword_16());
            		
            // InternalDsl.g:3105:3: ( (lv_value_17_0= RULE_STRING ) )
            // InternalDsl.g:3106:4: (lv_value_17_0= RULE_STRING )
            {
            // InternalDsl.g:3106:4: (lv_value_17_0= RULE_STRING )
            // InternalDsl.g:3107:5: lv_value_17_0= RULE_STRING
            {
            lv_value_17_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_17_0, grammarAccess.getGooglecalPUTAccess().getValueSTRINGTerminalRuleCall_17_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGooglecalPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_17_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_18=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_18, grammarAccess.getGooglecalPUTAccess().getRightCurlyBracketKeyword_18());
            		
            otherlv_19=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_19, grammarAccess.getGooglecalPUTAccess().getOnConditionKeyword_19());
            		
            // InternalDsl.g:3131:3: ( (lv_condition_20_0= ruleExpression ) )
            // InternalDsl.g:3132:4: (lv_condition_20_0= ruleExpression )
            {
            // InternalDsl.g:3132:4: (lv_condition_20_0= ruleExpression )
            // InternalDsl.g:3133:5: lv_condition_20_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getGooglecalPUTAccess().getConditionExpressionParserRuleCall_20_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_20_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGooglecalPUTRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_20_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGooglecalPUT"


    // $ANTLR start "entryRuleFBCLead"
    // InternalDsl.g:3154:1: entryRuleFBCLead returns [EObject current=null] : iv_ruleFBCLead= ruleFBCLead EOF ;
    public final EObject entryRuleFBCLead() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFBCLead = null;


        try {
            // InternalDsl.g:3154:48: (iv_ruleFBCLead= ruleFBCLead EOF )
            // InternalDsl.g:3155:2: iv_ruleFBCLead= ruleFBCLead EOF
            {
             newCompositeNode(grammarAccess.getFBCLeadRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFBCLead=ruleFBCLead();

            state._fsp--;

             current =iv_ruleFBCLead; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFBCLead"


    // $ANTLR start "ruleFBCLead"
    // InternalDsl.g:3161:1: ruleFBCLead returns [EObject current=null] : (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
    public final EObject ruleFBCLead() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_accessToken_4_0=null;
        Token otherlv_5=null;
        Token lv_appSecret_6_0=null;
        Token otherlv_7=null;
        Token lv_accountId_8_0=null;
        Token otherlv_9=null;
        Token lv_campaignId_10_0=null;
        Token otherlv_11=null;
        Token lv_target_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_value_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        EObject lv_condition_18_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:3167:2: ( (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:3168:2: (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3168:2: (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:3169:3: otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,55,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFBCLeadAccess().getFblcKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFBCLeadAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3177:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3178:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3178:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3179:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getFBCLeadAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFBCLeadAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:3199:3: ( (lv_accessToken_4_0= RULE_STRING ) )
            // InternalDsl.g:3200:4: (lv_accessToken_4_0= RULE_STRING )
            {
            // InternalDsl.g:3200:4: (lv_accessToken_4_0= RULE_STRING )
            // InternalDsl.g:3201:5: lv_accessToken_4_0= RULE_STRING
            {
            lv_accessToken_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_accessToken_4_0, grammarAccess.getFBCLeadAccess().getAccessTokenSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"accessToken",
            						lv_accessToken_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFBCLeadAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:3221:3: ( (lv_appSecret_6_0= RULE_STRING ) )
            // InternalDsl.g:3222:4: (lv_appSecret_6_0= RULE_STRING )
            {
            // InternalDsl.g:3222:4: (lv_appSecret_6_0= RULE_STRING )
            // InternalDsl.g:3223:5: lv_appSecret_6_0= RULE_STRING
            {
            lv_appSecret_6_0=(Token)match(input,RULE_STRING,FOLLOW_22); 

            					newLeafNode(lv_appSecret_6_0, grammarAccess.getFBCLeadAccess().getAppSecretSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"appSecret",
            						lv_appSecret_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFBCLeadAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:3243:3: ( (lv_accountId_8_0= RULE_STRING ) )
            // InternalDsl.g:3244:4: (lv_accountId_8_0= RULE_STRING )
            {
            // InternalDsl.g:3244:4: (lv_accountId_8_0= RULE_STRING )
            // InternalDsl.g:3245:5: lv_accountId_8_0= RULE_STRING
            {
            lv_accountId_8_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

            					newLeafNode(lv_accountId_8_0, grammarAccess.getFBCLeadAccess().getAccountIdSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"accountId",
            						lv_accountId_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,31,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFBCLeadAccess().getForCampaignKeyword_9());
            		
            // InternalDsl.g:3265:3: ( (lv_campaignId_10_0= RULE_STRING ) )
            // InternalDsl.g:3266:4: (lv_campaignId_10_0= RULE_STRING )
            {
            // InternalDsl.g:3266:4: (lv_campaignId_10_0= RULE_STRING )
            // InternalDsl.g:3267:5: lv_campaignId_10_0= RULE_STRING
            {
            lv_campaignId_10_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

            					newLeafNode(lv_campaignId_10_0, grammarAccess.getFBCLeadAccess().getCampaignIdSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"campaignId",
            						lv_campaignId_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFBCLeadAccess().getIntoKeyword_11());
            		
            // InternalDsl.g:3287:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:3288:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:3288:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:3289:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_target_12_0, grammarAccess.getFBCLeadAccess().getTargetSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getFBCLeadAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getFBCLeadAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:3313:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:3314:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:3314:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:3315:5: lv_value_15_0= RULE_STRING
            {
            lv_value_15_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_15_0, grammarAccess.getFBCLeadAccess().getValueSTRINGTerminalRuleCall_15_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBCLeadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_15_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_16=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_16, grammarAccess.getFBCLeadAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_17, grammarAccess.getFBCLeadAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:3339:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:3340:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:3340:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:3341:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getFBCLeadAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_18_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFBCLeadRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_18_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFBCLead"


    // $ANTLR start "entryRuleFBFormDownload"
    // InternalDsl.g:3362:1: entryRuleFBFormDownload returns [EObject current=null] : iv_ruleFBFormDownload= ruleFBFormDownload EOF ;
    public final EObject entryRuleFBFormDownload() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFBFormDownload = null;


        try {
            // InternalDsl.g:3362:55: (iv_ruleFBFormDownload= ruleFBFormDownload EOF )
            // InternalDsl.g:3363:2: iv_ruleFBFormDownload= ruleFBFormDownload EOF
            {
             newCompositeNode(grammarAccess.getFBFormDownloadRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFBFormDownload=ruleFBFormDownload();

            state._fsp--;

             current =iv_ruleFBFormDownload; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFBFormDownload"


    // $ANTLR start "ruleFBFormDownload"
    // InternalDsl.g:3369:1: ruleFBFormDownload returns [EObject current=null] : (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
    public final EObject ruleFBFormDownload() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_accessToken_4_0=null;
        Token otherlv_5=null;
        Token lv_appSecret_6_0=null;
        Token otherlv_7=null;
        Token lv_accountId_8_0=null;
        Token otherlv_9=null;
        Token lv_formId_10_0=null;
        Token otherlv_11=null;
        Token lv_target_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_value_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        EObject lv_condition_18_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:3375:2: ( (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:3376:2: (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3376:2: (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:3377:3: otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,56,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFBFormDownloadAccess().getFbfdKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFBFormDownloadAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3385:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3386:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3386:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3387:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getFBFormDownloadAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBFormDownloadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFBFormDownloadAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:3407:3: ( (lv_accessToken_4_0= RULE_STRING ) )
            // InternalDsl.g:3408:4: (lv_accessToken_4_0= RULE_STRING )
            {
            // InternalDsl.g:3408:4: (lv_accessToken_4_0= RULE_STRING )
            // InternalDsl.g:3409:5: lv_accessToken_4_0= RULE_STRING
            {
            lv_accessToken_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_accessToken_4_0, grammarAccess.getFBFormDownloadAccess().getAccessTokenSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBFormDownloadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"accessToken",
            						lv_accessToken_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFBFormDownloadAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:3429:3: ( (lv_appSecret_6_0= RULE_STRING ) )
            // InternalDsl.g:3430:4: (lv_appSecret_6_0= RULE_STRING )
            {
            // InternalDsl.g:3430:4: (lv_appSecret_6_0= RULE_STRING )
            // InternalDsl.g:3431:5: lv_appSecret_6_0= RULE_STRING
            {
            lv_appSecret_6_0=(Token)match(input,RULE_STRING,FOLLOW_22); 

            					newLeafNode(lv_appSecret_6_0, grammarAccess.getFBFormDownloadAccess().getAppSecretSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBFormDownloadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"appSecret",
            						lv_appSecret_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFBFormDownloadAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:3451:3: ( (lv_accountId_8_0= RULE_STRING ) )
            // InternalDsl.g:3452:4: (lv_accountId_8_0= RULE_STRING )
            {
            // InternalDsl.g:3452:4: (lv_accountId_8_0= RULE_STRING )
            // InternalDsl.g:3453:5: lv_accountId_8_0= RULE_STRING
            {
            lv_accountId_8_0=(Token)match(input,RULE_STRING,FOLLOW_37); 

            					newLeafNode(lv_accountId_8_0, grammarAccess.getFBFormDownloadAccess().getAccountIdSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBFormDownloadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"accountId",
            						lv_accountId_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,57,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFBFormDownloadAccess().getForFormKeyword_9());
            		
            // InternalDsl.g:3473:3: ( (lv_formId_10_0= RULE_STRING ) )
            // InternalDsl.g:3474:4: (lv_formId_10_0= RULE_STRING )
            {
            // InternalDsl.g:3474:4: (lv_formId_10_0= RULE_STRING )
            // InternalDsl.g:3475:5: lv_formId_10_0= RULE_STRING
            {
            lv_formId_10_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

            					newLeafNode(lv_formId_10_0, grammarAccess.getFBFormDownloadAccess().getFormIdSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBFormDownloadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"formId",
            						lv_formId_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFBFormDownloadAccess().getIntoKeyword_11());
            		
            // InternalDsl.g:3495:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:3496:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:3496:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:3497:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_target_12_0, grammarAccess.getFBFormDownloadAccess().getTargetSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBFormDownloadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getFBFormDownloadAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getFBFormDownloadAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:3521:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:3522:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:3522:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:3523:5: lv_value_15_0= RULE_STRING
            {
            lv_value_15_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_15_0, grammarAccess.getFBFormDownloadAccess().getValueSTRINGTerminalRuleCall_15_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBFormDownloadRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_15_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_16=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_16, grammarAccess.getFBFormDownloadAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_17, grammarAccess.getFBFormDownloadAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:3547:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:3548:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:3548:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:3549:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getFBFormDownloadAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_18_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFBFormDownloadRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_18_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFBFormDownload"


    // $ANTLR start "entryRuleDropfile"
    // InternalDsl.g:3570:1: entryRuleDropfile returns [EObject current=null] : iv_ruleDropfile= ruleDropfile EOF ;
    public final EObject entryRuleDropfile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDropfile = null;


        try {
            // InternalDsl.g:3570:49: (iv_ruleDropfile= ruleDropfile EOF )
            // InternalDsl.g:3571:2: iv_ruleDropfile= ruleDropfile EOF
            {
             newCompositeNode(grammarAccess.getDropfileRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDropfile=ruleDropfile();

            state._fsp--;

             current =iv_ruleDropfile; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDropfile"


    // $ANTLR start "ruleDropfile"
    // InternalDsl.g:3577:1: ruleDropfile returns [EObject current=null] : (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) ) ;
    public final EObject ruleDropfile() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_target_4_0=null;
        Token otherlv_5=null;
        EObject lv_condition_6_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:3583:2: ( (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) ) )
            // InternalDsl.g:3584:2: (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3584:2: (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) )
            // InternalDsl.g:3585:3: otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,58,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getDropfileAccess().getDropfileKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getDropfileAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3593:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3594:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3594:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3595:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_38); 

            					newLeafNode(lv_name_2_0, grammarAccess.getDropfileAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDropfileRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,59,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getDropfileAccess().getInPathKeyword_3());
            		
            // InternalDsl.g:3615:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:3616:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:3616:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:3617:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

            					newLeafNode(lv_target_4_0, grammarAccess.getDropfileAccess().getTargetSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDropfileRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_5, grammarAccess.getDropfileAccess().getOnConditionKeyword_5());
            		
            // InternalDsl.g:3637:3: ( (lv_condition_6_0= ruleExpression ) )
            // InternalDsl.g:3638:4: (lv_condition_6_0= ruleExpression )
            {
            // InternalDsl.g:3638:4: (lv_condition_6_0= ruleExpression )
            // InternalDsl.g:3639:5: lv_condition_6_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getDropfileAccess().getConditionExpressionParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_6_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDropfileRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_6_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDropfile"


    // $ANTLR start "entryRuleDoozle"
    // InternalDsl.g:3660:1: entryRuleDoozle returns [EObject current=null] : iv_ruleDoozle= ruleDoozle EOF ;
    public final EObject entryRuleDoozle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoozle = null;


        try {
            // InternalDsl.g:3660:47: (iv_ruleDoozle= ruleDoozle EOF )
            // InternalDsl.g:3661:2: iv_ruleDoozle= ruleDoozle EOF
            {
             newCompositeNode(grammarAccess.getDoozleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDoozle=ruleDoozle();

            state._fsp--;

             current =iv_ruleDoozle; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoozle"


    // $ANTLR start "ruleDoozle"
    // InternalDsl.g:3667:1: ruleDoozle returns [EObject current=null] : (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
    public final EObject ruleDoozle() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_target_4_0=null;
        Token otherlv_5=null;
        Token lv_on_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        AntlrDatatypeRuleToken lv_value_9_0 = null;

        EObject lv_condition_12_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:3673:2: ( (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:3674:2: (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3674:2: (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:3675:3: otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,60,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getDoozleAccess().getDoozleKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getDoozleAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3683:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3684:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3684:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3685:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_39); 

            					newLeafNode(lv_name_2_0, grammarAccess.getDoozleAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDoozleRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,61,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getDoozleAccess().getInTableKeyword_3());
            		
            // InternalDsl.g:3705:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:3706:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:3706:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:3707:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

            					newLeafNode(lv_target_4_0, grammarAccess.getDoozleAccess().getTargetSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDoozleRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,37,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getDoozleAccess().getOnKeyword_5());
            		
            // InternalDsl.g:3727:3: ( (lv_on_6_0= RULE_STRING ) )
            // InternalDsl.g:3728:4: (lv_on_6_0= RULE_STRING )
            {
            // InternalDsl.g:3728:4: (lv_on_6_0= RULE_STRING )
            // InternalDsl.g:3729:5: lv_on_6_0= RULE_STRING
            {
            lv_on_6_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_on_6_0, grammarAccess.getDoozleAccess().getOnSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDoozleRule());
            					}
            					setWithLastConsumed(
            						current,
            						"on",
            						lv_on_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getDoozleAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getDoozleAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:3753:3: ( (lv_value_9_0= ruleCreateStatement ) )
            // InternalDsl.g:3754:4: (lv_value_9_0= ruleCreateStatement )
            {
            // InternalDsl.g:3754:4: (lv_value_9_0= ruleCreateStatement )
            // InternalDsl.g:3755:5: lv_value_9_0= ruleCreateStatement
            {

            					newCompositeNode(grammarAccess.getDoozleAccess().getValueCreateStatementParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_9_0=ruleCreateStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDoozleRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_9_0,
            						"in.handyman.Dsl.CreateStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_10, grammarAccess.getDoozleAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_11, grammarAccess.getDoozleAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:3780:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:3781:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:3781:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:3782:5: lv_condition_12_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getDoozleAccess().getConditionExpressionParserRuleCall_12_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_12_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDoozleRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_12_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDoozle"


    // $ANTLR start "entryRuleRest"
    // InternalDsl.g:3803:1: entryRuleRest returns [EObject current=null] : iv_ruleRest= ruleRest EOF ;
    public final EObject entryRuleRest() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRest = null;


        try {
            // InternalDsl.g:3803:45: (iv_ruleRest= ruleRest EOF )
            // InternalDsl.g:3804:2: iv_ruleRest= ruleRest EOF
            {
             newCompositeNode(grammarAccess.getRestRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRest=ruleRest();

            state._fsp--;

             current =iv_ruleRest; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRest"


    // $ANTLR start "ruleRest"
    // InternalDsl.g:3810:1: ruleRest returns [EObject current=null] : (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) ) ;
    public final EObject ruleRest() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_authtoken_4_0=null;
        Token otherlv_5=null;
        Token lv_url_6_0=null;
        Token otherlv_7=null;
        Token lv_method_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token lv_resourcedatafrom_11_0=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token lv_headerdatafrom_17_0=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token lv_postdatafrom_23_0=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token lv_parentName_28_0=null;
        Token otherlv_31=null;
        Token otherlv_32=null;
        Token lv_ackdatato_33_0=null;
        Token otherlv_34=null;
        Token otherlv_35=null;
        Token otherlv_37=null;
        Token otherlv_38=null;
        Token otherlv_39=null;
        AntlrDatatypeRuleToken lv_urldata_14_0 = null;

        AntlrDatatypeRuleToken lv_headerdata_20_0 = null;

        AntlrDatatypeRuleToken lv_parentdata_29_0 = null;

        EObject lv_parts_30_0 = null;

        AntlrDatatypeRuleToken lv_ackdata_36_0 = null;

        EObject lv_condition_40_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:3816:2: ( (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) ) )
            // InternalDsl.g:3817:2: (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3817:2: (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) )
            // InternalDsl.g:3818:3: otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,62,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getRestAccess().getRestKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getRestAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3826:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3827:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3827:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3828:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getRestAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getRestAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:3848:3: ( (lv_authtoken_4_0= RULE_STRING ) )
            // InternalDsl.g:3849:4: (lv_authtoken_4_0= RULE_STRING )
            {
            // InternalDsl.g:3849:4: (lv_authtoken_4_0= RULE_STRING )
            // InternalDsl.g:3850:5: lv_authtoken_4_0= RULE_STRING
            {
            lv_authtoken_4_0=(Token)match(input,RULE_STRING,FOLLOW_40); 

            					newLeafNode(lv_authtoken_4_0, grammarAccess.getRestAccess().getAuthtokenSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"authtoken",
            						lv_authtoken_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,63,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getRestAccess().getWithUrlKeyword_5());
            		
            // InternalDsl.g:3870:3: ( (lv_url_6_0= RULE_STRING ) )
            // InternalDsl.g:3871:4: (lv_url_6_0= RULE_STRING )
            {
            // InternalDsl.g:3871:4: (lv_url_6_0= RULE_STRING )
            // InternalDsl.g:3872:5: lv_url_6_0= RULE_STRING
            {
            lv_url_6_0=(Token)match(input,RULE_STRING,FOLLOW_41); 

            					newLeafNode(lv_url_6_0, grammarAccess.getRestAccess().getUrlSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"url",
            						lv_url_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,64,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getRestAccess().getAndMethodAsKeyword_7());
            		
            // InternalDsl.g:3892:3: ( (lv_method_8_0= RULE_STRING ) )
            // InternalDsl.g:3893:4: (lv_method_8_0= RULE_STRING )
            {
            // InternalDsl.g:3893:4: (lv_method_8_0= RULE_STRING )
            // InternalDsl.g:3894:5: lv_method_8_0= RULE_STRING
            {
            lv_method_8_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					newLeafNode(lv_method_8_0, grammarAccess.getRestAccess().getMethodSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"method",
            						lv_method_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,12,FOLLOW_12); 

            			newLeafNode(otherlv_9, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_9());
            		
            otherlv_10=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getRestAccess().getFromKeyword_10());
            		
            // InternalDsl.g:3918:3: ( (lv_resourcedatafrom_11_0= RULE_STRING ) )
            // InternalDsl.g:3919:4: (lv_resourcedatafrom_11_0= RULE_STRING )
            {
            // InternalDsl.g:3919:4: (lv_resourcedatafrom_11_0= RULE_STRING )
            // InternalDsl.g:3920:5: lv_resourcedatafrom_11_0= RULE_STRING
            {
            lv_resourcedatafrom_11_0=(Token)match(input,RULE_STRING,FOLLOW_42); 

            					newLeafNode(lv_resourcedatafrom_11_0, grammarAccess.getRestAccess().getResourcedatafromSTRINGTerminalRuleCall_11_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"resourcedatafrom",
            						lv_resourcedatafrom_11_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_12=(Token)match(input,65,FOLLOW_4); 

            			newLeafNode(otherlv_12, grammarAccess.getRestAccess().getUpdateUrlWithKeyword_12());
            		
            otherlv_13=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_13());
            		
            // InternalDsl.g:3944:3: ( (lv_urldata_14_0= ruleSelectStatement ) )
            // InternalDsl.g:3945:4: (lv_urldata_14_0= ruleSelectStatement )
            {
            // InternalDsl.g:3945:4: (lv_urldata_14_0= ruleSelectStatement )
            // InternalDsl.g:3946:5: lv_urldata_14_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getRestAccess().getUrldataSelectStatementParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_8);
            lv_urldata_14_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRestRule());
            					}
            					set(
            						current,
            						"urldata",
            						lv_urldata_14_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_15=(Token)match(input,13,FOLLOW_12); 

            			newLeafNode(otherlv_15, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_15());
            		
            otherlv_16=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getRestAccess().getFromKeyword_16());
            		
            // InternalDsl.g:3971:3: ( (lv_headerdatafrom_17_0= RULE_STRING ) )
            // InternalDsl.g:3972:4: (lv_headerdatafrom_17_0= RULE_STRING )
            {
            // InternalDsl.g:3972:4: (lv_headerdatafrom_17_0= RULE_STRING )
            // InternalDsl.g:3973:5: lv_headerdatafrom_17_0= RULE_STRING
            {
            lv_headerdatafrom_17_0=(Token)match(input,RULE_STRING,FOLLOW_43); 

            					newLeafNode(lv_headerdatafrom_17_0, grammarAccess.getRestAccess().getHeaderdatafromSTRINGTerminalRuleCall_17_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"headerdatafrom",
            						lv_headerdatafrom_17_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_18=(Token)match(input,66,FOLLOW_4); 

            			newLeafNode(otherlv_18, grammarAccess.getRestAccess().getUpdateHeaderWithKeyword_18());
            		
            otherlv_19=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_19, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_19());
            		
            // InternalDsl.g:3997:3: ( (lv_headerdata_20_0= ruleSelectStatement ) )
            // InternalDsl.g:3998:4: (lv_headerdata_20_0= ruleSelectStatement )
            {
            // InternalDsl.g:3998:4: (lv_headerdata_20_0= ruleSelectStatement )
            // InternalDsl.g:3999:5: lv_headerdata_20_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getRestAccess().getHeaderdataSelectStatementParserRuleCall_20_0());
            				
            pushFollow(FOLLOW_8);
            lv_headerdata_20_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRestRule());
            					}
            					set(
            						current,
            						"headerdata",
            						lv_headerdata_20_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_21=(Token)match(input,13,FOLLOW_12); 

            			newLeafNode(otherlv_21, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_21());
            		
            otherlv_22=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_22, grammarAccess.getRestAccess().getFromKeyword_22());
            		
            // InternalDsl.g:4024:3: ( (lv_postdatafrom_23_0= RULE_STRING ) )
            // InternalDsl.g:4025:4: (lv_postdatafrom_23_0= RULE_STRING )
            {
            // InternalDsl.g:4025:4: (lv_postdatafrom_23_0= RULE_STRING )
            // InternalDsl.g:4026:5: lv_postdatafrom_23_0= RULE_STRING
            {
            lv_postdatafrom_23_0=(Token)match(input,RULE_STRING,FOLLOW_44); 

            					newLeafNode(lv_postdatafrom_23_0, grammarAccess.getRestAccess().getPostdatafromSTRINGTerminalRuleCall_23_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"postdatafrom",
            						lv_postdatafrom_23_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_24=(Token)match(input,67,FOLLOW_4); 

            			newLeafNode(otherlv_24, grammarAccess.getRestAccess().getUpdateBodyWithKeyword_24());
            		
            otherlv_25=(Token)match(input,12,FOLLOW_45); 

            			newLeafNode(otherlv_25, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_25());
            		
            otherlv_26=(Token)match(input,68,FOLLOW_11); 

            			newLeafNode(otherlv_26, grammarAccess.getRestAccess().getParentKeyword_26());
            		
            otherlv_27=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_27, grammarAccess.getRestAccess().getAsKeyword_27());
            		
            // InternalDsl.g:4058:3: ( (lv_parentName_28_0= RULE_STRING ) )
            // InternalDsl.g:4059:4: (lv_parentName_28_0= RULE_STRING )
            {
            // InternalDsl.g:4059:4: (lv_parentName_28_0= RULE_STRING )
            // InternalDsl.g:4060:5: lv_parentName_28_0= RULE_STRING
            {
            lv_parentName_28_0=(Token)match(input,RULE_STRING,FOLLOW_3); 

            					newLeafNode(lv_parentName_28_0, grammarAccess.getRestAccess().getParentNameSTRINGTerminalRuleCall_28_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"parentName",
            						lv_parentName_28_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalDsl.g:4076:3: ( (lv_parentdata_29_0= ruleSelectStatement ) )
            // InternalDsl.g:4077:4: (lv_parentdata_29_0= ruleSelectStatement )
            {
            // InternalDsl.g:4077:4: (lv_parentdata_29_0= ruleSelectStatement )
            // InternalDsl.g:4078:5: lv_parentdata_29_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getRestAccess().getParentdataSelectStatementParserRuleCall_29_0());
            				
            pushFollow(FOLLOW_46);
            lv_parentdata_29_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRestRule());
            					}
            					set(
            						current,
            						"parentdata",
            						lv_parentdata_29_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalDsl.g:4095:3: ( (lv_parts_30_0= ruleRestPart ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==70) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalDsl.g:4096:4: (lv_parts_30_0= ruleRestPart )
            	    {
            	    // InternalDsl.g:4096:4: (lv_parts_30_0= ruleRestPart )
            	    // InternalDsl.g:4097:5: lv_parts_30_0= ruleRestPart
            	    {

            	    					newCompositeNode(grammarAccess.getRestAccess().getPartsRestPartParserRuleCall_30_0());
            	    				
            	    pushFollow(FOLLOW_46);
            	    lv_parts_30_0=ruleRestPart();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getRestRule());
            	    					}
            	    					add(
            	    						current,
            	    						"parts",
            	    						lv_parts_30_0,
            	    						"in.handyman.Dsl.RestPart");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_31=(Token)match(input,13,FOLLOW_24); 

            			newLeafNode(otherlv_31, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_31());
            		
            otherlv_32=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_32, grammarAccess.getRestAccess().getIntoKeyword_32());
            		
            // InternalDsl.g:4122:3: ( (lv_ackdatato_33_0= RULE_STRING ) )
            // InternalDsl.g:4123:4: (lv_ackdatato_33_0= RULE_STRING )
            {
            // InternalDsl.g:4123:4: (lv_ackdatato_33_0= RULE_STRING )
            // InternalDsl.g:4124:5: lv_ackdatato_33_0= RULE_STRING
            {
            lv_ackdatato_33_0=(Token)match(input,RULE_STRING,FOLLOW_47); 

            					newLeafNode(lv_ackdatato_33_0, grammarAccess.getRestAccess().getAckdatatoSTRINGTerminalRuleCall_33_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"ackdatato",
            						lv_ackdatato_33_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_34=(Token)match(input,69,FOLLOW_4); 

            			newLeafNode(otherlv_34, grammarAccess.getRestAccess().getStoreAckAtKeyword_34());
            		
            otherlv_35=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_35, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_35());
            		
            // InternalDsl.g:4148:3: ( (lv_ackdata_36_0= ruleSelectStatement ) )
            // InternalDsl.g:4149:4: (lv_ackdata_36_0= ruleSelectStatement )
            {
            // InternalDsl.g:4149:4: (lv_ackdata_36_0= ruleSelectStatement )
            // InternalDsl.g:4150:5: lv_ackdata_36_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getRestAccess().getAckdataSelectStatementParserRuleCall_36_0());
            				
            pushFollow(FOLLOW_8);
            lv_ackdata_36_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRestRule());
            					}
            					set(
            						current,
            						"ackdata",
            						lv_ackdata_36_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_37=(Token)match(input,13,FOLLOW_8); 

            			newLeafNode(otherlv_37, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_37());
            		
            otherlv_38=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_38, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_38());
            		
            otherlv_39=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_39, grammarAccess.getRestAccess().getOnConditionKeyword_39());
            		
            // InternalDsl.g:4179:3: ( (lv_condition_40_0= ruleExpression ) )
            // InternalDsl.g:4180:4: (lv_condition_40_0= ruleExpression )
            {
            // InternalDsl.g:4180:4: (lv_condition_40_0= ruleExpression )
            // InternalDsl.g:4181:5: lv_condition_40_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getRestAccess().getConditionExpressionParserRuleCall_40_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_40_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRestRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_40_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRest"


    // $ANTLR start "entryRuleRestPart"
    // InternalDsl.g:4202:1: entryRuleRestPart returns [EObject current=null] : iv_ruleRestPart= ruleRestPart EOF ;
    public final EObject entryRuleRestPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRestPart = null;


        try {
            // InternalDsl.g:4202:49: (iv_ruleRestPart= ruleRestPart EOF )
            // InternalDsl.g:4203:2: iv_ruleRestPart= ruleRestPart EOF
            {
             newCompositeNode(grammarAccess.getRestPartRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRestPart=ruleRestPart();

            state._fsp--;

             current =iv_ruleRestPart; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRestPart"


    // $ANTLR start "ruleRestPart"
    // InternalDsl.g:4209:1: ruleRestPart returns [EObject current=null] : (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) ) ;
    public final EObject ruleRestPart() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_partName_2_0=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_partData_4_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:4215:2: ( (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) ) )
            // InternalDsl.g:4216:2: (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) )
            {
            // InternalDsl.g:4216:2: (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) )
            // InternalDsl.g:4217:3: otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) )
            {
            otherlv_0=(Token)match(input,70,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getRestPartAccess().getPartKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getRestPartAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4225:3: ( (lv_partName_2_0= RULE_STRING ) )
            // InternalDsl.g:4226:4: (lv_partName_2_0= RULE_STRING )
            {
            // InternalDsl.g:4226:4: (lv_partName_2_0= RULE_STRING )
            // InternalDsl.g:4227:5: lv_partName_2_0= RULE_STRING
            {
            lv_partName_2_0=(Token)match(input,RULE_STRING,FOLLOW_48); 

            					newLeafNode(lv_partName_2_0, grammarAccess.getRestPartAccess().getPartNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRestPartRule());
            					}
            					setWithLastConsumed(
            						current,
            						"partName",
            						lv_partName_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,71,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getRestPartAccess().getWithKeyword_3());
            		
            // InternalDsl.g:4247:3: ( (lv_partData_4_0= ruleSelectStatement ) )
            // InternalDsl.g:4248:4: (lv_partData_4_0= ruleSelectStatement )
            {
            // InternalDsl.g:4248:4: (lv_partData_4_0= ruleSelectStatement )
            // InternalDsl.g:4249:5: lv_partData_4_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getRestPartAccess().getPartDataSelectStatementParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_partData_4_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRestPartRule());
            					}
            					set(
            						current,
            						"partData",
            						lv_partData_4_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRestPart"


    // $ANTLR start "entryRuleTrelloGET"
    // InternalDsl.g:4270:1: entryRuleTrelloGET returns [EObject current=null] : iv_ruleTrelloGET= ruleTrelloGET EOF ;
    public final EObject entryRuleTrelloGET() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTrelloGET = null;


        try {
            // InternalDsl.g:4270:50: (iv_ruleTrelloGET= ruleTrelloGET EOF )
            // InternalDsl.g:4271:2: iv_ruleTrelloGET= ruleTrelloGET EOF
            {
             newCompositeNode(grammarAccess.getTrelloGETRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTrelloGET=ruleTrelloGET();

            state._fsp--;

             current =iv_ruleTrelloGET; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTrelloGET"


    // $ANTLR start "ruleTrelloGET"
    // InternalDsl.g:4277:1: ruleTrelloGET returns [EObject current=null] : (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
    public final EObject ruleTrelloGET() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_authtoken_4_0=null;
        Token otherlv_5=null;
        Token lv_key_6_0=null;
        Token otherlv_7=null;
        Token lv_useraccount_8_0=null;
        Token otherlv_9=null;
        Token lv_board_10_0=null;
        Token otherlv_11=null;
        Token lv_target_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_value_15_0 = null;

        EObject lv_condition_18_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:4283:2: ( (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:4284:2: (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4284:2: (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:4285:3: otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,72,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getTrelloGETAccess().getTrellogetKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTrelloGETAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4293:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4294:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4294:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4295:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getTrelloGETAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getTrelloGETAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:4315:3: ( (lv_authtoken_4_0= RULE_STRING ) )
            // InternalDsl.g:4316:4: (lv_authtoken_4_0= RULE_STRING )
            {
            // InternalDsl.g:4316:4: (lv_authtoken_4_0= RULE_STRING )
            // InternalDsl.g:4317:5: lv_authtoken_4_0= RULE_STRING
            {
            lv_authtoken_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_authtoken_4_0, grammarAccess.getTrelloGETAccess().getAuthtokenSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"authtoken",
            						lv_authtoken_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getTrelloGETAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:4337:3: ( (lv_key_6_0= RULE_STRING ) )
            // InternalDsl.g:4338:4: (lv_key_6_0= RULE_STRING )
            {
            // InternalDsl.g:4338:4: (lv_key_6_0= RULE_STRING )
            // InternalDsl.g:4339:5: lv_key_6_0= RULE_STRING
            {
            lv_key_6_0=(Token)match(input,RULE_STRING,FOLLOW_22); 

            					newLeafNode(lv_key_6_0, grammarAccess.getTrelloGETAccess().getKeySTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getTrelloGETAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:4359:3: ( (lv_useraccount_8_0= RULE_STRING ) )
            // InternalDsl.g:4360:4: (lv_useraccount_8_0= RULE_STRING )
            {
            // InternalDsl.g:4360:4: (lv_useraccount_8_0= RULE_STRING )
            // InternalDsl.g:4361:5: lv_useraccount_8_0= RULE_STRING
            {
            lv_useraccount_8_0=(Token)match(input,RULE_STRING,FOLLOW_49); 

            					newLeafNode(lv_useraccount_8_0, grammarAccess.getTrelloGETAccess().getUseraccountSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"useraccount",
            						lv_useraccount_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,73,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getTrelloGETAccess().getFromBoardKeyword_9());
            		
            // InternalDsl.g:4381:3: ( (lv_board_10_0= RULE_STRING ) )
            // InternalDsl.g:4382:4: (lv_board_10_0= RULE_STRING )
            {
            // InternalDsl.g:4382:4: (lv_board_10_0= RULE_STRING )
            // InternalDsl.g:4383:5: lv_board_10_0= RULE_STRING
            {
            lv_board_10_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_board_10_0, grammarAccess.getTrelloGETAccess().getBoardSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"board",
            						lv_board_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getTrelloGETAccess().getToKeyword_11());
            		
            // InternalDsl.g:4403:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:4404:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:4404:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:4405:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_target_12_0, grammarAccess.getTrelloGETAccess().getTargetSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloGETRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getTrelloGETAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getTrelloGETAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:4429:3: ( (lv_value_15_0= ruleSelectStatement ) )
            // InternalDsl.g:4430:4: (lv_value_15_0= ruleSelectStatement )
            {
            // InternalDsl.g:4430:4: (lv_value_15_0= ruleSelectStatement )
            // InternalDsl.g:4431:5: lv_value_15_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getTrelloGETAccess().getValueSelectStatementParserRuleCall_15_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_15_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTrelloGETRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_15_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_16=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_16, grammarAccess.getTrelloGETAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_17, grammarAccess.getTrelloGETAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:4456:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:4457:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:4457:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:4458:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getTrelloGETAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_18_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTrelloGETRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_18_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTrelloGET"


    // $ANTLR start "entryRuleTrelloPUT"
    // InternalDsl.g:4479:1: entryRuleTrelloPUT returns [EObject current=null] : iv_ruleTrelloPUT= ruleTrelloPUT EOF ;
    public final EObject entryRuleTrelloPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTrelloPUT = null;


        try {
            // InternalDsl.g:4479:50: (iv_ruleTrelloPUT= ruleTrelloPUT EOF )
            // InternalDsl.g:4480:2: iv_ruleTrelloPUT= ruleTrelloPUT EOF
            {
             newCompositeNode(grammarAccess.getTrelloPUTRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTrelloPUT=ruleTrelloPUT();

            state._fsp--;

             current =iv_ruleTrelloPUT; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTrelloPUT"


    // $ANTLR start "ruleTrelloPUT"
    // InternalDsl.g:4486:1: ruleTrelloPUT returns [EObject current=null] : (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
    public final EObject ruleTrelloPUT() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_authtoken_4_0=null;
        Token otherlv_5=null;
        Token lv_key_6_0=null;
        Token otherlv_7=null;
        Token lv_useraccount_8_0=null;
        Token otherlv_9=null;
        Token lv_list_10_0=null;
        Token otherlv_11=null;
        Token lv_source_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        AntlrDatatypeRuleToken lv_value_15_0 = null;

        EObject lv_condition_18_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:4492:2: ( (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:4493:2: (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4493:2: (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:4494:3: otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,74,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getTrelloPUTAccess().getTrelloputKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTrelloPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4502:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4503:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4503:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4504:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getTrelloPUTAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getTrelloPUTAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:4524:3: ( (lv_authtoken_4_0= RULE_STRING ) )
            // InternalDsl.g:4525:4: (lv_authtoken_4_0= RULE_STRING )
            {
            // InternalDsl.g:4525:4: (lv_authtoken_4_0= RULE_STRING )
            // InternalDsl.g:4526:5: lv_authtoken_4_0= RULE_STRING
            {
            lv_authtoken_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

            					newLeafNode(lv_authtoken_4_0, grammarAccess.getTrelloPUTAccess().getAuthtokenSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"authtoken",
            						lv_authtoken_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,29,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getTrelloPUTAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:4546:3: ( (lv_key_6_0= RULE_STRING ) )
            // InternalDsl.g:4547:4: (lv_key_6_0= RULE_STRING )
            {
            // InternalDsl.g:4547:4: (lv_key_6_0= RULE_STRING )
            // InternalDsl.g:4548:5: lv_key_6_0= RULE_STRING
            {
            lv_key_6_0=(Token)match(input,RULE_STRING,FOLLOW_22); 

            					newLeafNode(lv_key_6_0, grammarAccess.getTrelloPUTAccess().getKeySTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getTrelloPUTAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:4568:3: ( (lv_useraccount_8_0= RULE_STRING ) )
            // InternalDsl.g:4569:4: (lv_useraccount_8_0= RULE_STRING )
            {
            // InternalDsl.g:4569:4: (lv_useraccount_8_0= RULE_STRING )
            // InternalDsl.g:4570:5: lv_useraccount_8_0= RULE_STRING
            {
            lv_useraccount_8_0=(Token)match(input,RULE_STRING,FOLLOW_50); 

            					newLeafNode(lv_useraccount_8_0, grammarAccess.getTrelloPUTAccess().getUseraccountSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"useraccount",
            						lv_useraccount_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,75,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getTrelloPUTAccess().getForListKeyword_9());
            		
            // InternalDsl.g:4590:3: ( (lv_list_10_0= RULE_STRING ) )
            // InternalDsl.g:4591:4: (lv_list_10_0= RULE_STRING )
            {
            // InternalDsl.g:4591:4: (lv_list_10_0= RULE_STRING )
            // InternalDsl.g:4592:5: lv_list_10_0= RULE_STRING
            {
            lv_list_10_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_list_10_0, grammarAccess.getTrelloPUTAccess().getListSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"list",
            						lv_list_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getTrelloPUTAccess().getFromSourceKeyword_11());
            		
            // InternalDsl.g:4612:3: ( (lv_source_12_0= RULE_STRING ) )
            // InternalDsl.g:4613:4: (lv_source_12_0= RULE_STRING )
            {
            // InternalDsl.g:4613:4: (lv_source_12_0= RULE_STRING )
            // InternalDsl.g:4614:5: lv_source_12_0= RULE_STRING
            {
            lv_source_12_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_source_12_0, grammarAccess.getTrelloPUTAccess().getSourceSTRINGTerminalRuleCall_12_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTrelloPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_12_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_13=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getTrelloPUTAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getTrelloPUTAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:4638:3: ( (lv_value_15_0= ruleSelectStatement ) )
            // InternalDsl.g:4639:4: (lv_value_15_0= ruleSelectStatement )
            {
            // InternalDsl.g:4639:4: (lv_value_15_0= ruleSelectStatement )
            // InternalDsl.g:4640:5: lv_value_15_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getTrelloPUTAccess().getValueSelectStatementParserRuleCall_15_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_15_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTrelloPUTRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_15_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_16=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_16, grammarAccess.getTrelloPUTAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_17, grammarAccess.getTrelloPUTAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:4665:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:4666:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:4666:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:4667:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getTrelloPUTAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_18_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTrelloPUTRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_18_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTrelloPUT"


    // $ANTLR start "entryRuleFetch"
    // InternalDsl.g:4688:1: entryRuleFetch returns [EObject current=null] : iv_ruleFetch= ruleFetch EOF ;
    public final EObject entryRuleFetch() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFetch = null;


        try {
            // InternalDsl.g:4688:46: (iv_ruleFetch= ruleFetch EOF )
            // InternalDsl.g:4689:2: iv_ruleFetch= ruleFetch EOF
            {
             newCompositeNode(grammarAccess.getFetchRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFetch=ruleFetch();

            state._fsp--;

             current =iv_ruleFetch; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFetch"


    // $ANTLR start "ruleFetch"
    // InternalDsl.g:4695:1: ruleFetch returns [EObject current=null] : (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) ;
    public final EObject ruleFetch() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_source_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_value_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        EObject lv_condition_10_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:4701:2: ( (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) )
            // InternalDsl.g:4702:2: (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4702:2: (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            // InternalDsl.g:4703:3: otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,76,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFetchAccess().getAssignKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFetchAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4711:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4712:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4712:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4713:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_51); 

            					newLeafNode(lv_name_2_0, grammarAccess.getFetchAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFetchRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,77,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFetchAccess().getSourceKeyword_3());
            		
            // InternalDsl.g:4733:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:4734:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:4734:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:4735:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_source_4_0, grammarAccess.getFetchAccess().getSourceSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFetchRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_5, grammarAccess.getFetchAccess().getUsingKeyword_5());
            		
            otherlv_6=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_6, grammarAccess.getFetchAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalDsl.g:4759:3: ( (lv_value_7_0= RULE_STRING ) )
            // InternalDsl.g:4760:4: (lv_value_7_0= RULE_STRING )
            {
            // InternalDsl.g:4760:4: (lv_value_7_0= RULE_STRING )
            // InternalDsl.g:4761:5: lv_value_7_0= RULE_STRING
            {
            lv_value_7_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_7_0, grammarAccess.getFetchAccess().getValueSTRINGTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFetchRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_7_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_8=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_8, grammarAccess.getFetchAccess().getRightCurlyBracketKeyword_8());
            		
            otherlv_9=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_9, grammarAccess.getFetchAccess().getOnConditionKeyword_9());
            		
            // InternalDsl.g:4785:3: ( (lv_condition_10_0= ruleExpression ) )
            // InternalDsl.g:4786:4: (lv_condition_10_0= ruleExpression )
            {
            // InternalDsl.g:4786:4: (lv_condition_10_0= ruleExpression )
            // InternalDsl.g:4787:5: lv_condition_10_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getFetchAccess().getConditionExpressionParserRuleCall_10_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_10_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFetchRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_10_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFetch"


    // $ANTLR start "entryRuleCallprocess"
    // InternalDsl.g:4808:1: entryRuleCallprocess returns [EObject current=null] : iv_ruleCallprocess= ruleCallprocess EOF ;
    public final EObject entryRuleCallprocess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCallprocess = null;


        try {
            // InternalDsl.g:4808:52: (iv_ruleCallprocess= ruleCallprocess EOF )
            // InternalDsl.g:4809:2: iv_ruleCallprocess= ruleCallprocess EOF
            {
             newCompositeNode(grammarAccess.getCallprocessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCallprocess=ruleCallprocess();

            state._fsp--;

             current =iv_ruleCallprocess; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCallprocess"


    // $ANTLR start "ruleCallprocess"
    // InternalDsl.g:4815:1: ruleCallprocess returns [EObject current=null] : (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
    public final EObject ruleCallprocess() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_target_4_0=null;
        Token otherlv_5=null;
        Token lv_source_6_0=null;
        Token otherlv_7=null;
        Token lv_datasource_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_value_11_0 = null;

        EObject lv_condition_14_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:4821:2: ( (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:4822:2: (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4822:2: (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:4823:3: otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,78,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getCallprocessAccess().getCallprocessKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getCallprocessAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4831:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4832:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4832:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4833:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_52); 

            					newLeafNode(lv_name_2_0, grammarAccess.getCallprocessAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCallprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,79,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getCallprocessAccess().getWithTargetKeyword_3());
            		
            // InternalDsl.g:4853:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:4854:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:4854:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:4855:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_53); 

            					newLeafNode(lv_target_4_0, grammarAccess.getCallprocessAccess().getTargetSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCallprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,80,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getCallprocessAccess().getFromFileKeyword_5());
            		
            // InternalDsl.g:4875:3: ( (lv_source_6_0= RULE_STRING ) )
            // InternalDsl.g:4876:4: (lv_source_6_0= RULE_STRING )
            {
            // InternalDsl.g:4876:4: (lv_source_6_0= RULE_STRING )
            // InternalDsl.g:4877:5: lv_source_6_0= RULE_STRING
            {
            lv_source_6_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_source_6_0, grammarAccess.getCallprocessAccess().getSourceSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCallprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getCallprocessAccess().getUsingKeyword_7());
            		
            // InternalDsl.g:4897:3: ( (lv_datasource_8_0= RULE_STRING ) )
            // InternalDsl.g:4898:4: (lv_datasource_8_0= RULE_STRING )
            {
            // InternalDsl.g:4898:4: (lv_datasource_8_0= RULE_STRING )
            // InternalDsl.g:4899:5: lv_datasource_8_0= RULE_STRING
            {
            lv_datasource_8_0=(Token)match(input,RULE_STRING,FOLLOW_54); 

            					newLeafNode(lv_datasource_8_0, grammarAccess.getCallprocessAccess().getDatasourceSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCallprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"datasource",
            						lv_datasource_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,81,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getCallprocessAccess().getForEveryKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getCallprocessAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:4923:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:4924:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:4924:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:4925:5: lv_value_11_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getCallprocessAccess().getValueSelectStatementParserRuleCall_11_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_11_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCallprocessRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_11_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_12=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_12, grammarAccess.getCallprocessAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_13, grammarAccess.getCallprocessAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:4950:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:4951:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:4951:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:4952:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getCallprocessAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_14_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCallprocessRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_14_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCallprocess"


    // $ANTLR start "entryRuleForkprocess"
    // InternalDsl.g:4973:1: entryRuleForkprocess returns [EObject current=null] : iv_ruleForkprocess= ruleForkprocess EOF ;
    public final EObject entryRuleForkprocess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForkprocess = null;


        try {
            // InternalDsl.g:4973:52: (iv_ruleForkprocess= ruleForkprocess EOF )
            // InternalDsl.g:4974:2: iv_ruleForkprocess= ruleForkprocess EOF
            {
             newCompositeNode(grammarAccess.getForkprocessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForkprocess=ruleForkprocess();

            state._fsp--;

             current =iv_ruleForkprocess; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForkprocess"


    // $ANTLR start "ruleForkprocess"
    // InternalDsl.g:4980:1: ruleForkprocess returns [EObject current=null] : (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
    public final EObject ruleForkprocess() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_target_4_0=null;
        Token otherlv_5=null;
        Token lv_source_6_0=null;
        Token otherlv_7=null;
        Token lv_datasource_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_value_11_0 = null;

        EObject lv_condition_14_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:4986:2: ( (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:4987:2: (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4987:2: (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:4988:3: otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,82,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getForkprocessAccess().getForkprocessKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getForkprocessAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4996:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4997:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4997:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4998:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_52); 

            					newLeafNode(lv_name_2_0, grammarAccess.getForkprocessAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getForkprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,79,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getForkprocessAccess().getWithTargetKeyword_3());
            		
            // InternalDsl.g:5018:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:5019:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:5019:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:5020:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_53); 

            					newLeafNode(lv_target_4_0, grammarAccess.getForkprocessAccess().getTargetSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getForkprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,80,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getForkprocessAccess().getFromFileKeyword_5());
            		
            // InternalDsl.g:5040:3: ( (lv_source_6_0= RULE_STRING ) )
            // InternalDsl.g:5041:4: (lv_source_6_0= RULE_STRING )
            {
            // InternalDsl.g:5041:4: (lv_source_6_0= RULE_STRING )
            // InternalDsl.g:5042:5: lv_source_6_0= RULE_STRING
            {
            lv_source_6_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_source_6_0, grammarAccess.getForkprocessAccess().getSourceSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getForkprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getForkprocessAccess().getUsingKeyword_7());
            		
            // InternalDsl.g:5062:3: ( (lv_datasource_8_0= RULE_STRING ) )
            // InternalDsl.g:5063:4: (lv_datasource_8_0= RULE_STRING )
            {
            // InternalDsl.g:5063:4: (lv_datasource_8_0= RULE_STRING )
            // InternalDsl.g:5064:5: lv_datasource_8_0= RULE_STRING
            {
            lv_datasource_8_0=(Token)match(input,RULE_STRING,FOLLOW_54); 

            					newLeafNode(lv_datasource_8_0, grammarAccess.getForkprocessAccess().getDatasourceSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getForkprocessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"datasource",
            						lv_datasource_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,81,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getForkprocessAccess().getForEveryKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getForkprocessAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:5088:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:5089:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:5089:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:5090:5: lv_value_11_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getForkprocessAccess().getValueSelectStatementParserRuleCall_11_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_11_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForkprocessRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_11_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_12=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_12, grammarAccess.getForkprocessAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_13, grammarAccess.getForkprocessAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:5115:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:5116:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:5116:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:5117:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getForkprocessAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_14_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForkprocessRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_14_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForkprocess"


    // $ANTLR start "entryRuleUpdatedaudit"
    // InternalDsl.g:5138:1: entryRuleUpdatedaudit returns [EObject current=null] : iv_ruleUpdatedaudit= ruleUpdatedaudit EOF ;
    public final EObject entryRuleUpdatedaudit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUpdatedaudit = null;


        try {
            // InternalDsl.g:5138:53: (iv_ruleUpdatedaudit= ruleUpdatedaudit EOF )
            // InternalDsl.g:5139:2: iv_ruleUpdatedaudit= ruleUpdatedaudit EOF
            {
             newCompositeNode(grammarAccess.getUpdatedauditRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUpdatedaudit=ruleUpdatedaudit();

            state._fsp--;

             current =iv_ruleUpdatedaudit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUpdatedaudit"


    // $ANTLR start "ruleUpdatedaudit"
    // InternalDsl.g:5145:1: ruleUpdatedaudit returns [EObject current=null] : (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
    public final EObject ruleUpdatedaudit() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_logsink_4_0=null;
        Token otherlv_5=null;
        Token lv_datasource_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        AntlrDatatypeRuleToken lv_value_9_0 = null;

        EObject lv_condition_12_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:5151:2: ( (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:5152:2: (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5152:2: (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:5153:3: otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,83,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getUpdatedauditAccess().getLogKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getUpdatedauditAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5161:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5162:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5162:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5163:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_55); 

            					newLeafNode(lv_name_2_0, grammarAccess.getUpdatedauditAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getUpdatedauditRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,84,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getUpdatedauditAccess().getLogtoKeyword_3());
            		
            // InternalDsl.g:5183:3: ( (lv_logsink_4_0= RULE_STRING ) )
            // InternalDsl.g:5184:4: (lv_logsink_4_0= RULE_STRING )
            {
            // InternalDsl.g:5184:4: (lv_logsink_4_0= RULE_STRING )
            // InternalDsl.g:5185:5: lv_logsink_4_0= RULE_STRING
            {
            lv_logsink_4_0=(Token)match(input,RULE_STRING,FOLLOW_56); 

            					newLeafNode(lv_logsink_4_0, grammarAccess.getUpdatedauditAccess().getLogsinkSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getUpdatedauditRule());
            					}
            					setWithLastConsumed(
            						current,
            						"logsink",
            						lv_logsink_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,85,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getUpdatedauditAccess().getInKeyword_5());
            		
            // InternalDsl.g:5205:3: ( (lv_datasource_6_0= RULE_STRING ) )
            // InternalDsl.g:5206:4: (lv_datasource_6_0= RULE_STRING )
            {
            // InternalDsl.g:5206:4: (lv_datasource_6_0= RULE_STRING )
            // InternalDsl.g:5207:5: lv_datasource_6_0= RULE_STRING
            {
            lv_datasource_6_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_datasource_6_0, grammarAccess.getUpdatedauditAccess().getDatasourceSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getUpdatedauditRule());
            					}
            					setWithLastConsumed(
            						current,
            						"datasource",
            						lv_datasource_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getUpdatedauditAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getUpdatedauditAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:5231:3: ( (lv_value_9_0= ruleSelectStatement ) )
            // InternalDsl.g:5232:4: (lv_value_9_0= ruleSelectStatement )
            {
            // InternalDsl.g:5232:4: (lv_value_9_0= ruleSelectStatement )
            // InternalDsl.g:5233:5: lv_value_9_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getUpdatedauditAccess().getValueSelectStatementParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_9_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUpdatedauditRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_9_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_10, grammarAccess.getUpdatedauditAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_11, grammarAccess.getUpdatedauditAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:5258:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:5259:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:5259:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:5260:5: lv_condition_12_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getUpdatedauditAccess().getConditionExpressionParserRuleCall_12_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_12_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getUpdatedauditRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_12_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUpdatedaudit"


    // $ANTLR start "entryRuleClickSendSms"
    // InternalDsl.g:5281:1: entryRuleClickSendSms returns [EObject current=null] : iv_ruleClickSendSms= ruleClickSendSms EOF ;
    public final EObject entryRuleClickSendSms() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClickSendSms = null;


        try {
            // InternalDsl.g:5281:53: (iv_ruleClickSendSms= ruleClickSendSms EOF )
            // InternalDsl.g:5282:2: iv_ruleClickSendSms= ruleClickSendSms EOF
            {
             newCompositeNode(grammarAccess.getClickSendSmsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleClickSendSms=ruleClickSendSms();

            state._fsp--;

             current =iv_ruleClickSendSms; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClickSendSms"


    // $ANTLR start "ruleClickSendSms"
    // InternalDsl.g:5288:1: ruleClickSendSms returns [EObject current=null] : (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
    public final EObject ruleClickSendSms() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_userid_4_0=null;
        Token otherlv_5=null;
        Token lv_securityKey_6_0=null;
        Token otherlv_7=null;
        Token lv_target_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token lv_value_11_0=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        EObject lv_condition_14_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:5294:2: ( (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:5295:2: (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5295:2: (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:5296:3: otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,86,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getClickSendSmsAccess().getC2smsKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getClickSendSmsAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5304:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5305:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5305:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5306:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_57); 

            					newLeafNode(lv_name_2_0, grammarAccess.getClickSendSmsAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClickSendSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,87,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getClickSendSmsAccess().getWithUserKeyword_3());
            		
            // InternalDsl.g:5326:3: ( (lv_userid_4_0= RULE_STRING ) )
            // InternalDsl.g:5327:4: (lv_userid_4_0= RULE_STRING )
            {
            // InternalDsl.g:5327:4: (lv_userid_4_0= RULE_STRING )
            // InternalDsl.g:5328:5: lv_userid_4_0= RULE_STRING
            {
            lv_userid_4_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_userid_4_0, grammarAccess.getClickSendSmsAccess().getUseridSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClickSendSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"userid",
            						lv_userid_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getClickSendSmsAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:5348:3: ( (lv_securityKey_6_0= RULE_STRING ) )
            // InternalDsl.g:5349:4: (lv_securityKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:5349:4: (lv_securityKey_6_0= RULE_STRING )
            // InternalDsl.g:5350:5: lv_securityKey_6_0= RULE_STRING
            {
            lv_securityKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_securityKey_6_0, grammarAccess.getClickSendSmsAccess().getSecurityKeySTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClickSendSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"securityKey",
            						lv_securityKey_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getClickSendSmsAccess().getFromSourceKeyword_7());
            		
            // InternalDsl.g:5370:3: ( (lv_target_8_0= RULE_STRING ) )
            // InternalDsl.g:5371:4: (lv_target_8_0= RULE_STRING )
            {
            // InternalDsl.g:5371:4: (lv_target_8_0= RULE_STRING )
            // InternalDsl.g:5372:5: lv_target_8_0= RULE_STRING
            {
            lv_target_8_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_target_8_0, grammarAccess.getClickSendSmsAccess().getTargetSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClickSendSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getClickSendSmsAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getClickSendSmsAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:5396:3: ( (lv_value_11_0= RULE_STRING ) )
            // InternalDsl.g:5397:4: (lv_value_11_0= RULE_STRING )
            {
            // InternalDsl.g:5397:4: (lv_value_11_0= RULE_STRING )
            // InternalDsl.g:5398:5: lv_value_11_0= RULE_STRING
            {
            lv_value_11_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_11_0, grammarAccess.getClickSendSmsAccess().getValueSTRINGTerminalRuleCall_11_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getClickSendSmsRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_11_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_12=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_12, grammarAccess.getClickSendSmsAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_13, grammarAccess.getClickSendSmsAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:5422:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:5423:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:5423:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:5424:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getClickSendSmsAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_14_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getClickSendSmsRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_14_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClickSendSms"


    // $ANTLR start "entryRuleSlackPUT"
    // InternalDsl.g:5445:1: entryRuleSlackPUT returns [EObject current=null] : iv_ruleSlackPUT= ruleSlackPUT EOF ;
    public final EObject entryRuleSlackPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSlackPUT = null;


        try {
            // InternalDsl.g:5445:49: (iv_ruleSlackPUT= ruleSlackPUT EOF )
            // InternalDsl.g:5446:2: iv_ruleSlackPUT= ruleSlackPUT EOF
            {
             newCompositeNode(grammarAccess.getSlackPUTRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSlackPUT=ruleSlackPUT();

            state._fsp--;

             current =iv_ruleSlackPUT; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSlackPUT"


    // $ANTLR start "ruleSlackPUT"
    // InternalDsl.g:5452:1: ruleSlackPUT returns [EObject current=null] : (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
    public final EObject ruleSlackPUT() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_team_4_0=null;
        Token otherlv_5=null;
        Token lv_channel_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token lv_value_9_0=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        EObject lv_condition_12_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:5458:2: ( (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:5459:2: (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5459:2: (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:5460:3: otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,88,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getSlackPUTAccess().getSlackputKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getSlackPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5468:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5469:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5469:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5470:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_name_2_0, grammarAccess.getSlackPUTAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSlackPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getSlackPUTAccess().getToKeyword_3());
            		
            // InternalDsl.g:5490:3: ( (lv_team_4_0= RULE_STRING ) )
            // InternalDsl.g:5491:4: (lv_team_4_0= RULE_STRING )
            {
            // InternalDsl.g:5491:4: (lv_team_4_0= RULE_STRING )
            // InternalDsl.g:5492:5: lv_team_4_0= RULE_STRING
            {
            lv_team_4_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

            					newLeafNode(lv_team_4_0, grammarAccess.getSlackPUTAccess().getTeamSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSlackPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"team",
            						lv_team_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,37,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getSlackPUTAccess().getOnKeyword_5());
            		
            // InternalDsl.g:5512:3: ( (lv_channel_6_0= RULE_STRING ) )
            // InternalDsl.g:5513:4: (lv_channel_6_0= RULE_STRING )
            {
            // InternalDsl.g:5513:4: (lv_channel_6_0= RULE_STRING )
            // InternalDsl.g:5514:5: lv_channel_6_0= RULE_STRING
            {
            lv_channel_6_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_channel_6_0, grammarAccess.getSlackPUTAccess().getChannelSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSlackPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"channel",
            						lv_channel_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getSlackPUTAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getSlackPUTAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:5538:3: ( (lv_value_9_0= RULE_STRING ) )
            // InternalDsl.g:5539:4: (lv_value_9_0= RULE_STRING )
            {
            // InternalDsl.g:5539:4: (lv_value_9_0= RULE_STRING )
            // InternalDsl.g:5540:5: lv_value_9_0= RULE_STRING
            {
            lv_value_9_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_value_9_0, grammarAccess.getSlackPUTAccess().getValueSTRINGTerminalRuleCall_9_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSlackPUTRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_9_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_10=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_10, grammarAccess.getSlackPUTAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_11, grammarAccess.getSlackPUTAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:5564:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:5565:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:5565:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:5566:5: lv_condition_12_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getSlackPUTAccess().getConditionExpressionParserRuleCall_12_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_12_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSlackPUTRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_12_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSlackPUT"


    // $ANTLR start "entryRuleCopydata"
    // InternalDsl.g:5587:1: entryRuleCopydata returns [EObject current=null] : iv_ruleCopydata= ruleCopydata EOF ;
    public final EObject entryRuleCopydata() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCopydata = null;


        try {
            // InternalDsl.g:5587:49: (iv_ruleCopydata= ruleCopydata EOF )
            // InternalDsl.g:5588:2: iv_ruleCopydata= ruleCopydata EOF
            {
             newCompositeNode(grammarAccess.getCopydataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCopydata=ruleCopydata();

            state._fsp--;

             current =iv_ruleCopydata; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCopydata"


    // $ANTLR start "ruleCopydata"
    // InternalDsl.g:5594:1: ruleCopydata returns [EObject current=null] : (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
    public final EObject ruleCopydata() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_source_4_0=null;
        Token otherlv_5=null;
        Token lv_to_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        AntlrDatatypeRuleToken lv_value_9_0 = null;

        EObject lv_condition_12_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:5600:2: ( (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:5601:2: (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5601:2: (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:5602:3: otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,89,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getCopydataAccess().getCopydataKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getCopydataAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5610:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5611:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5611:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5612:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_name_2_0, grammarAccess.getCopydataAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCopydataRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getCopydataAccess().getFromKeyword_3());
            		
            // InternalDsl.g:5632:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:5633:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:5633:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:5634:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_source_4_0, grammarAccess.getCopydataAccess().getSourceSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCopydataRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getCopydataAccess().getToKeyword_5());
            		
            // InternalDsl.g:5654:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:5655:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:5655:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:5656:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_to_6_0, grammarAccess.getCopydataAccess().getToSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCopydataRule());
            					}
            					setWithLastConsumed(
            						current,
            						"to",
            						lv_to_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getCopydataAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getCopydataAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:5680:3: ( (lv_value_9_0= ruleSelectStatement ) )
            // InternalDsl.g:5681:4: (lv_value_9_0= ruleSelectStatement )
            {
            // InternalDsl.g:5681:4: (lv_value_9_0= ruleSelectStatement )
            // InternalDsl.g:5682:5: lv_value_9_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getCopydataAccess().getValueSelectStatementParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_9_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCopydataRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_9_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_10, grammarAccess.getCopydataAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_11, grammarAccess.getCopydataAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:5707:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:5708:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:5708:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:5709:5: lv_condition_12_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getCopydataAccess().getConditionExpressionParserRuleCall_12_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_12_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCopydataRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_12_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCopydata"


    // $ANTLR start "entryRuleWriteCsv"
    // InternalDsl.g:5730:1: entryRuleWriteCsv returns [EObject current=null] : iv_ruleWriteCsv= ruleWriteCsv EOF ;
    public final EObject entryRuleWriteCsv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWriteCsv = null;


        try {
            // InternalDsl.g:5730:49: (iv_ruleWriteCsv= ruleWriteCsv EOF )
            // InternalDsl.g:5731:2: iv_ruleWriteCsv= ruleWriteCsv EOF
            {
             newCompositeNode(grammarAccess.getWriteCsvRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWriteCsv=ruleWriteCsv();

            state._fsp--;

             current =iv_ruleWriteCsv; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWriteCsv"


    // $ANTLR start "ruleWriteCsv"
    // InternalDsl.g:5737:1: ruleWriteCsv returns [EObject current=null] : (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
    public final EObject ruleWriteCsv() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_source_4_0=null;
        Token otherlv_5=null;
        Token lv_to_6_0=null;
        Token otherlv_7=null;
        Token lv_delim_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_value_11_0 = null;

        EObject lv_condition_14_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:5743:2: ( (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:5744:2: (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5744:2: (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:5745:3: otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,90,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getWriteCsvAccess().getWritecsvKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getWriteCsvAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5753:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5754:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5754:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5755:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_name_2_0, grammarAccess.getWriteCsvAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWriteCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getWriteCsvAccess().getFromKeyword_3());
            		
            // InternalDsl.g:5775:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:5776:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:5776:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:5777:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_source_4_0, grammarAccess.getWriteCsvAccess().getSourceSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWriteCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getWriteCsvAccess().getToKeyword_5());
            		
            // InternalDsl.g:5797:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:5798:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:5798:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:5799:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_48); 

            					newLeafNode(lv_to_6_0, grammarAccess.getWriteCsvAccess().getToSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWriteCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"to",
            						lv_to_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,71,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getWriteCsvAccess().getWithKeyword_7());
            		
            // InternalDsl.g:5819:3: ( (lv_delim_8_0= RULE_STRING ) )
            // InternalDsl.g:5820:4: (lv_delim_8_0= RULE_STRING )
            {
            // InternalDsl.g:5820:4: (lv_delim_8_0= RULE_STRING )
            // InternalDsl.g:5821:5: lv_delim_8_0= RULE_STRING
            {
            lv_delim_8_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_delim_8_0, grammarAccess.getWriteCsvAccess().getDelimSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWriteCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"delim",
            						lv_delim_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getWriteCsvAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getWriteCsvAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:5845:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:5846:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:5846:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:5847:5: lv_value_11_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getWriteCsvAccess().getValueSelectStatementParserRuleCall_11_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_11_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWriteCsvRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_11_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_12=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_12, grammarAccess.getWriteCsvAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_13, grammarAccess.getWriteCsvAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:5872:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:5873:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:5873:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:5874:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getWriteCsvAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_14_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWriteCsvRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_14_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWriteCsv"


    // $ANTLR start "entryRuleLoadCsv"
    // InternalDsl.g:5895:1: entryRuleLoadCsv returns [EObject current=null] : iv_ruleLoadCsv= ruleLoadCsv EOF ;
    public final EObject entryRuleLoadCsv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadCsv = null;


        try {
            // InternalDsl.g:5895:48: (iv_ruleLoadCsv= ruleLoadCsv EOF )
            // InternalDsl.g:5896:2: iv_ruleLoadCsv= ruleLoadCsv EOF
            {
             newCompositeNode(grammarAccess.getLoadCsvRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLoadCsv=ruleLoadCsv();

            state._fsp--;

             current =iv_ruleLoadCsv; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLoadCsv"


    // $ANTLR start "ruleLoadCsv"
    // InternalDsl.g:5902:1: ruleLoadCsv returns [EObject current=null] : (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
    public final EObject ruleLoadCsv() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_source_4_0=null;
        Token otherlv_5=null;
        Token lv_to_6_0=null;
        Token otherlv_7=null;
        Token lv_delim_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_value_11_0 = null;

        EObject lv_condition_14_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:5908:2: ( (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:5909:2: (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5909:2: (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:5910:3: otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,91,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getLoadCsvAccess().getLoadcsvKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getLoadCsvAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5918:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5919:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5919:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5920:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_name_2_0, grammarAccess.getLoadCsvAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLoadCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getLoadCsvAccess().getFromKeyword_3());
            		
            // InternalDsl.g:5940:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:5941:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:5941:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:5942:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            					newLeafNode(lv_source_4_0, grammarAccess.getLoadCsvAccess().getSourceSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLoadCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"source",
            						lv_source_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getLoadCsvAccess().getToKeyword_5());
            		
            // InternalDsl.g:5962:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:5963:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:5963:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:5964:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_48); 

            					newLeafNode(lv_to_6_0, grammarAccess.getLoadCsvAccess().getToSTRINGTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLoadCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"to",
            						lv_to_6_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_7=(Token)match(input,71,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getLoadCsvAccess().getWithKeyword_7());
            		
            // InternalDsl.g:5984:3: ( (lv_delim_8_0= RULE_STRING ) )
            // InternalDsl.g:5985:4: (lv_delim_8_0= RULE_STRING )
            {
            // InternalDsl.g:5985:4: (lv_delim_8_0= RULE_STRING )
            // InternalDsl.g:5986:5: lv_delim_8_0= RULE_STRING
            {
            lv_delim_8_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_delim_8_0, grammarAccess.getLoadCsvAccess().getDelimSTRINGTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLoadCsvRule());
            					}
            					setWithLastConsumed(
            						current,
            						"delim",
            						lv_delim_8_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_9=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getLoadCsvAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getLoadCsvAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:6010:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:6011:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:6011:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:6012:5: lv_value_11_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getLoadCsvAccess().getValueSelectStatementParserRuleCall_11_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_11_0=ruleSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLoadCsvRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_11_0,
            						"in.handyman.Dsl.SelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_12=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_12, grammarAccess.getLoadCsvAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_13, grammarAccess.getLoadCsvAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:6037:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:6038:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:6038:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:6039:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getLoadCsvAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_14_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLoadCsvRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_14_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLoadCsv"


    // $ANTLR start "entryRuleTransform"
    // InternalDsl.g:6060:1: entryRuleTransform returns [EObject current=null] : iv_ruleTransform= ruleTransform EOF ;
    public final EObject entryRuleTransform() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTransform = null;


        try {
            // InternalDsl.g:6060:50: (iv_ruleTransform= ruleTransform EOF )
            // InternalDsl.g:6061:2: iv_ruleTransform= ruleTransform EOF
            {
             newCompositeNode(grammarAccess.getTransformRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTransform=ruleTransform();

            state._fsp--;

             current =iv_ruleTransform; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTransform"


    // $ANTLR start "ruleTransform"
    // InternalDsl.g:6067:1: ruleTransform returns [EObject current=null] : (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) ;
    public final EObject ruleTransform() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_on_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_value_7_0 = null;

        EObject lv_condition_10_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:6073:2: ( (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) )
            // InternalDsl.g:6074:2: (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            {
            // InternalDsl.g:6074:2: (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            // InternalDsl.g:6075:3: otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,92,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getTransformAccess().getTransformKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTransformAccess().getAsKeyword_1());
            		
            // InternalDsl.g:6083:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:6084:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:6084:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:6085:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

            					newLeafNode(lv_name_2_0, grammarAccess.getTransformAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTransformRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,37,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getTransformAccess().getOnKeyword_3());
            		
            // InternalDsl.g:6105:3: ( (lv_on_4_0= RULE_STRING ) )
            // InternalDsl.g:6106:4: (lv_on_4_0= RULE_STRING )
            {
            // InternalDsl.g:6106:4: (lv_on_4_0= RULE_STRING )
            // InternalDsl.g:6107:5: lv_on_4_0= RULE_STRING
            {
            lv_on_4_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            					newLeafNode(lv_on_4_0, grammarAccess.getTransformAccess().getOnSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTransformRule());
            					}
            					setWithLastConsumed(
            						current,
            						"on",
            						lv_on_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_5, grammarAccess.getTransformAccess().getUsingKeyword_5());
            		
            otherlv_6=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_6, grammarAccess.getTransformAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalDsl.g:6131:3: ( (lv_value_7_0= ruleNonSelectStatement ) )
            // InternalDsl.g:6132:4: (lv_value_7_0= ruleNonSelectStatement )
            {
            // InternalDsl.g:6132:4: (lv_value_7_0= ruleNonSelectStatement )
            // InternalDsl.g:6133:5: lv_value_7_0= ruleNonSelectStatement
            {

            					newCompositeNode(grammarAccess.getTransformAccess().getValueNonSelectStatementParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_7_0=ruleNonSelectStatement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTransformRule());
            					}
            					add(
            						current,
            						"value",
            						lv_value_7_0,
            						"in.handyman.Dsl.NonSelectStatement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,13,FOLLOW_17); 

            			newLeafNode(otherlv_8, grammarAccess.getTransformAccess().getRightCurlyBracketKeyword_8());
            		
            otherlv_9=(Token)match(input,24,FOLLOW_18); 

            			newLeafNode(otherlv_9, grammarAccess.getTransformAccess().getOnConditionKeyword_9());
            		
            // InternalDsl.g:6158:3: ( (lv_condition_10_0= ruleExpression ) )
            // InternalDsl.g:6159:4: (lv_condition_10_0= ruleExpression )
            {
            // InternalDsl.g:6159:4: (lv_condition_10_0= ruleExpression )
            // InternalDsl.g:6160:5: lv_condition_10_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getTransformAccess().getConditionExpressionParserRuleCall_10_0());
            				
            pushFollow(FOLLOW_2);
            lv_condition_10_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTransformRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_10_0,
            						"in.handyman.Dsl.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTransform"


    // $ANTLR start "entryRuleSelectStatement"
    // InternalDsl.g:6181:1: entryRuleSelectStatement returns [String current=null] : iv_ruleSelectStatement= ruleSelectStatement EOF ;
    public final String entryRuleSelectStatement() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSelectStatement = null;


        try {
            // InternalDsl.g:6181:55: (iv_ruleSelectStatement= ruleSelectStatement EOF )
            // InternalDsl.g:6182:2: iv_ruleSelectStatement= ruleSelectStatement EOF
            {
             newCompositeNode(grammarAccess.getSelectStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSelectStatement=ruleSelectStatement();

            state._fsp--;

             current =iv_ruleSelectStatement.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelectStatement"


    // $ANTLR start "ruleSelectStatement"
    // InternalDsl.g:6188:1: ruleSelectStatement returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleSelectStatement() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalDsl.g:6194:2: (this_STRING_0= RULE_STRING )
            // InternalDsl.g:6195:2: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		current.merge(this_STRING_0);
            	

            		newLeafNode(this_STRING_0, grammarAccess.getSelectStatementAccess().getSTRINGTerminalRuleCall());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectStatement"


    // $ANTLR start "entryRuleNonSelectStatement"
    // InternalDsl.g:6205:1: entryRuleNonSelectStatement returns [String current=null] : iv_ruleNonSelectStatement= ruleNonSelectStatement EOF ;
    public final String entryRuleNonSelectStatement() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNonSelectStatement = null;


        try {
            // InternalDsl.g:6205:58: (iv_ruleNonSelectStatement= ruleNonSelectStatement EOF )
            // InternalDsl.g:6206:2: iv_ruleNonSelectStatement= ruleNonSelectStatement EOF
            {
             newCompositeNode(grammarAccess.getNonSelectStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNonSelectStatement=ruleNonSelectStatement();

            state._fsp--;

             current =iv_ruleNonSelectStatement.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNonSelectStatement"


    // $ANTLR start "ruleNonSelectStatement"
    // InternalDsl.g:6212:1: ruleNonSelectStatement returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleNonSelectStatement() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalDsl.g:6218:2: (this_STRING_0= RULE_STRING )
            // InternalDsl.g:6219:2: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		current.merge(this_STRING_0);
            	

            		newLeafNode(this_STRING_0, grammarAccess.getNonSelectStatementAccess().getSTRINGTerminalRuleCall());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNonSelectStatement"


    // $ANTLR start "entryRuleCreateStatement"
    // InternalDsl.g:6229:1: entryRuleCreateStatement returns [String current=null] : iv_ruleCreateStatement= ruleCreateStatement EOF ;
    public final String entryRuleCreateStatement() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCreateStatement = null;


        try {
            // InternalDsl.g:6229:55: (iv_ruleCreateStatement= ruleCreateStatement EOF )
            // InternalDsl.g:6230:2: iv_ruleCreateStatement= ruleCreateStatement EOF
            {
             newCompositeNode(grammarAccess.getCreateStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCreateStatement=ruleCreateStatement();

            state._fsp--;

             current =iv_ruleCreateStatement.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCreateStatement"


    // $ANTLR start "ruleCreateStatement"
    // InternalDsl.g:6236:1: ruleCreateStatement returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleCreateStatement() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalDsl.g:6242:2: (this_STRING_0= RULE_STRING )
            // InternalDsl.g:6243:2: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		current.merge(this_STRING_0);
            	

            		newLeafNode(this_STRING_0, grammarAccess.getCreateStatementAccess().getSTRINGTerminalRuleCall());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCreateStatement"


    // $ANTLR start "entryRuleExpression"
    // InternalDsl.g:6253:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalDsl.g:6253:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalDsl.g:6254:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalDsl.g:6260:1: ruleExpression returns [EObject current=null] : (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_lhs_1_0=null;
        Token lv_rhs_3_0=null;
        AntlrDatatypeRuleToken lv_operator_2_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:6266:2: ( (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) ) )
            // InternalDsl.g:6267:2: (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:6267:2: (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) )
            // InternalDsl.g:6268:3: otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,93,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getExpressionAccess().getIfKeyword_0());
            		
            // InternalDsl.g:6272:3: ( (lv_lhs_1_0= RULE_STRING ) )
            // InternalDsl.g:6273:4: (lv_lhs_1_0= RULE_STRING )
            {
            // InternalDsl.g:6273:4: (lv_lhs_1_0= RULE_STRING )
            // InternalDsl.g:6274:5: lv_lhs_1_0= RULE_STRING
            {
            lv_lhs_1_0=(Token)match(input,RULE_STRING,FOLLOW_58); 

            					newLeafNode(lv_lhs_1_0, grammarAccess.getExpressionAccess().getLhsSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExpressionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"lhs",
            						lv_lhs_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalDsl.g:6290:3: ( (lv_operator_2_0= ruleOperator ) )
            // InternalDsl.g:6291:4: (lv_operator_2_0= ruleOperator )
            {
            // InternalDsl.g:6291:4: (lv_operator_2_0= ruleOperator )
            // InternalDsl.g:6292:5: lv_operator_2_0= ruleOperator
            {

            					newCompositeNode(grammarAccess.getExpressionAccess().getOperatorOperatorParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_3);
            lv_operator_2_0=ruleOperator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExpressionRule());
            					}
            					set(
            						current,
            						"operator",
            						lv_operator_2_0,
            						"in.handyman.Dsl.Operator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalDsl.g:6309:3: ( (lv_rhs_3_0= RULE_STRING ) )
            // InternalDsl.g:6310:4: (lv_rhs_3_0= RULE_STRING )
            {
            // InternalDsl.g:6310:4: (lv_rhs_3_0= RULE_STRING )
            // InternalDsl.g:6311:5: lv_rhs_3_0= RULE_STRING
            {
            lv_rhs_3_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_rhs_3_0, grammarAccess.getExpressionAccess().getRhsSTRINGTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExpressionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"rhs",
            						lv_rhs_3_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleOperator"
    // InternalDsl.g:6331:1: entryRuleOperator returns [String current=null] : iv_ruleOperator= ruleOperator EOF ;
    public final String entryRuleOperator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOperator = null;


        try {
            // InternalDsl.g:6331:48: (iv_ruleOperator= ruleOperator EOF )
            // InternalDsl.g:6332:2: iv_ruleOperator= ruleOperator EOF
            {
             newCompositeNode(grammarAccess.getOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperator=ruleOperator();

            state._fsp--;

             current =iv_ruleOperator.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperator"


    // $ANTLR start "ruleOperator"
    // InternalDsl.g:6338:1: ruleOperator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' ) ;
    public final AntlrDatatypeRuleToken ruleOperator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalDsl.g:6344:2: ( (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' ) )
            // InternalDsl.g:6345:2: (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' )
            {
            // InternalDsl.g:6345:2: (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 94:
                {
                alt6=1;
                }
                break;
            case 95:
                {
                alt6=2;
                }
                break;
            case 96:
                {
                alt6=3;
                }
                break;
            case 97:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalDsl.g:6346:3: kw= '<'
                    {
                    kw=(Token)match(input,94,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getLessThanSignKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalDsl.g:6352:3: kw= '>'
                    {
                    kw=(Token)match(input,95,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getGreaterThanSignKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalDsl.g:6358:3: kw= '=='
                    {
                    kw=(Token)match(input,96,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getEqualsSignEqualsSignKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalDsl.g:6364:3: kw= 'contains'
                    {
                    kw=(Token)match(input,97,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getContainsKeyword_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperator"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x55F18C160C022000L,0x000000001F4C5500L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000002000L,0x0000000000000040L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000000L,0x00000003C0000000L});

}