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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'efblc'", "'as'", "'secured-by'", "'with-key'", "'through-user'", "'for-campaign'", "'into'", "'using'", "'on-condition'", "'elasticget'", "'from'", "'to'", "'java'", "'name-sake-db'", "'fbdp'", "'on'", "'auth-by'", "'for-group'", "'from-source'", "'with-class'", "'fbrn'", "'smsleadssms'", "'with-sender'", "'through-account'", "'do-dryrun-with'", "'abort'", "'gcontact-fetchall'", "'for-project'", "'on-behalf-of'", "'to-target'", "'sendmail'", "'gcontact'", "'gcalendar'", "'fblc'", "'fbfd'", "'for-form'", "'dropfile'", "'in-path'", "'doozle'", "'in-table'", "'rest'", "'with-url'", "'and-method-as'", "'update-url-with'", "'update-header-with'", "'update-body-with'", "'parent'", "'store-ack-at'", "'part'", "'with'", "'trelloget'", "'from-board'", "'trelloput'", "'for-list'", "'assign'", "'source'", "'callprocess'", "'with-target'", "'from-file'", "'for-every'", "'forkprocess'", "'log'", "'logto'", "'in'", "'c2sms'", "'with-user'", "'slackput'", "'copydata'", "'writecsv'", "'loadcsv'", "'transform'", "'if'", "'<'", "'>'", "'=='", "'contains'"
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

                if ( (LA1_0==17||LA1_0==26||LA1_0==29||LA1_0==31||(LA1_0>=37 && LA1_0<=38)||(LA1_0>=42 && LA1_0<=43)||(LA1_0>=47 && LA1_0<=51)||LA1_0==53||LA1_0==55||LA1_0==57||LA1_0==67||LA1_0==69||LA1_0==71||LA1_0==73||(LA1_0>=77 && LA1_0<=78)||LA1_0==81||(LA1_0>=83 && LA1_0<=87)) ) {
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

                if ( (LA2_0==17||LA2_0==26||LA2_0==29||LA2_0==31||(LA2_0>=37 && LA2_0<=38)||(LA2_0>=42 && LA2_0<=43)||(LA2_0>=47 && LA2_0<=51)||LA2_0==53||LA2_0==55||LA2_0==57||LA2_0==67||LA2_0==69||LA2_0==71||LA2_0==73||(LA2_0>=77 && LA2_0<=78)||LA2_0==81||(LA2_0>=83 && LA2_0<=87)) ) {
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

                if ( (LA3_0==17||LA3_0==26||LA3_0==29||LA3_0==31||(LA3_0>=37 && LA3_0<=38)||(LA3_0>=42 && LA3_0<=43)||(LA3_0>=47 && LA3_0<=51)||LA3_0==53||LA3_0==55||LA3_0==57||LA3_0==67||LA3_0==69||LA3_0==71||LA3_0==73||(LA3_0>=77 && LA3_0<=78)||LA3_0==81||(LA3_0>=83 && LA3_0<=87)) ) {
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
    // InternalDsl.g:381:1: ruleAction returns [EObject current=null] : (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET ) ;
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



        	enterRule();

        try {
            // InternalDsl.g:387:2: ( (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET ) )
            // InternalDsl.g:388:2: (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET )
            {
            // InternalDsl.g:388:2: (this_Copydata_0= ruleCopydata | this_LoadCsv_1= ruleLoadCsv | this_WriteCsv_2= ruleWriteCsv | this_Transform_3= ruleTransform | this_GooglecalPUT_4= ruleGooglecalPUT | this_SlackPUT_5= ruleSlackPUT | this_ClickSendSms_6= ruleClickSendSms | this_Updatedaudit_7= ruleUpdatedaudit | this_Callprocess_8= ruleCallprocess | this_Forkprocess_9= ruleForkprocess | this_Fetch_10= ruleFetch | this_TrelloPUT_11= ruleTrelloPUT | this_TrelloGET_12= ruleTrelloGET | this_Rest_13= ruleRest | this_Doozle_14= ruleDoozle | this_Dropfile_15= ruleDropfile | this_FBCLead_16= ruleFBCLead | this_FBFormDownload_17= ruleFBFormDownload | this_SendMail_18= ruleSendMail | this_GooglecontactPUT_19= ruleGooglecontactPUT | this_GooglecontactSelectAll_20= ruleGooglecontactSelectAll | this_Abort_21= ruleAbort | this_SmsLeadSms_22= ruleSmsLeadSms | this_FirebaseReactiveNotification_23= ruleFirebaseReactiveNotification | this_FirebaseDatabasePut_24= ruleFirebaseDatabasePut | this_ExecJava_25= ruleExecJava | this_ElasticFBCLead_26= ruleElasticFBCLead | this_ElasticGET_27= ruleElasticGET )
            int alt4=28;
            switch ( input.LA(1) ) {
            case 84:
                {
                alt4=1;
                }
                break;
            case 86:
                {
                alt4=2;
                }
                break;
            case 85:
                {
                alt4=3;
                }
                break;
            case 87:
                {
                alt4=4;
                }
                break;
            case 49:
                {
                alt4=5;
                }
                break;
            case 83:
                {
                alt4=6;
                }
                break;
            case 81:
                {
                alt4=7;
                }
                break;
            case 78:
                {
                alt4=8;
                }
                break;
            case 73:
                {
                alt4=9;
                }
                break;
            case 77:
                {
                alt4=10;
                }
                break;
            case 71:
                {
                alt4=11;
                }
                break;
            case 69:
                {
                alt4=12;
                }
                break;
            case 67:
                {
                alt4=13;
                }
                break;
            case 57:
                {
                alt4=14;
                }
                break;
            case 55:
                {
                alt4=15;
                }
                break;
            case 53:
                {
                alt4=16;
                }
                break;
            case 50:
                {
                alt4=17;
                }
                break;
            case 51:
                {
                alt4=18;
                }
                break;
            case 47:
                {
                alt4=19;
                }
                break;
            case 48:
                {
                alt4=20;
                }
                break;
            case 43:
                {
                alt4=21;
                }
                break;
            case 42:
                {
                alt4=22;
                }
                break;
            case 38:
                {
                alt4=23;
                }
                break;
            case 37:
                {
                alt4=24;
                }
                break;
            case 31:
                {
                alt4=25;
                }
                break;
            case 29:
                {
                alt4=26;
                }
                break;
            case 17:
                {
                alt4=27;
                }
                break;
            case 26:
                {
                alt4=28;
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


    // $ANTLR start "entryRuleElasticFBCLead"
    // InternalDsl.g:644:1: entryRuleElasticFBCLead returns [EObject current=null] : iv_ruleElasticFBCLead= ruleElasticFBCLead EOF ;
    public final EObject entryRuleElasticFBCLead() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElasticFBCLead = null;


        try {
            // InternalDsl.g:644:55: (iv_ruleElasticFBCLead= ruleElasticFBCLead EOF )
            // InternalDsl.g:645:2: iv_ruleElasticFBCLead= ruleElasticFBCLead EOF
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
    // InternalDsl.g:651:1: ruleElasticFBCLead returns [EObject current=null] : (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:657:2: ( (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:658:2: (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:658:2: (otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:659:3: otherlv_0= 'efblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getElasticFBCLeadAccess().getEfblcKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getElasticFBCLeadAccess().getAsKeyword_1());
            		
            // InternalDsl.g:667:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:668:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:668:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:669:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getElasticFBCLeadAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:689:3: ( (lv_accessToken_4_0= RULE_STRING ) )
            // InternalDsl.g:690:4: (lv_accessToken_4_0= RULE_STRING )
            {
            // InternalDsl.g:690:4: (lv_accessToken_4_0= RULE_STRING )
            // InternalDsl.g:691:5: lv_accessToken_4_0= RULE_STRING
            {
            lv_accessToken_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getElasticFBCLeadAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:711:3: ( (lv_appSecret_6_0= RULE_STRING ) )
            // InternalDsl.g:712:4: (lv_appSecret_6_0= RULE_STRING )
            {
            // InternalDsl.g:712:4: (lv_appSecret_6_0= RULE_STRING )
            // InternalDsl.g:713:5: lv_appSecret_6_0= RULE_STRING
            {
            lv_appSecret_6_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

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

            otherlv_7=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getElasticFBCLeadAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:733:3: ( (lv_accountId_8_0= RULE_STRING ) )
            // InternalDsl.g:734:4: (lv_accountId_8_0= RULE_STRING )
            {
            // InternalDsl.g:734:4: (lv_accountId_8_0= RULE_STRING )
            // InternalDsl.g:735:5: lv_accountId_8_0= RULE_STRING
            {
            lv_accountId_8_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

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

            otherlv_9=(Token)match(input,22,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getElasticFBCLeadAccess().getForCampaignKeyword_9());
            		
            // InternalDsl.g:755:3: ( (lv_campaignId_10_0= RULE_STRING ) )
            // InternalDsl.g:756:4: (lv_campaignId_10_0= RULE_STRING )
            {
            // InternalDsl.g:756:4: (lv_campaignId_10_0= RULE_STRING )
            // InternalDsl.g:757:5: lv_campaignId_10_0= RULE_STRING
            {
            lv_campaignId_10_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

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

            otherlv_11=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getElasticFBCLeadAccess().getIntoKeyword_11());
            		
            // InternalDsl.g:777:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:778:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:778:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:779:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_13=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getElasticFBCLeadAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getElasticFBCLeadAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:803:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:804:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:804:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:805:5: lv_value_15_0= RULE_STRING
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

            otherlv_16=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_16, grammarAccess.getElasticFBCLeadAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_17, grammarAccess.getElasticFBCLeadAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:829:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:830:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:830:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:831:5: lv_condition_18_0= ruleExpression
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
    // InternalDsl.g:852:1: entryRuleElasticGET returns [EObject current=null] : iv_ruleElasticGET= ruleElasticGET EOF ;
    public final EObject entryRuleElasticGET() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElasticGET = null;


        try {
            // InternalDsl.g:852:51: (iv_ruleElasticGET= ruleElasticGET EOF )
            // InternalDsl.g:853:2: iv_ruleElasticGET= ruleElasticGET EOF
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
    // InternalDsl.g:859:1: ruleElasticGET returns [EObject current=null] : (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:865:2: ( (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:866:2: (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:866:2: (otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:867:3: otherlv_0= 'elasticget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getElasticGETAccess().getElasticgetKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getElasticGETAccess().getAsKeyword_1());
            		
            // InternalDsl.g:875:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:876:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:876:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:877:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

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

            otherlv_3=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getElasticGETAccess().getFromKeyword_3());
            		
            // InternalDsl.g:897:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:898:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:898:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:899:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

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

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getElasticGETAccess().getToKeyword_5());
            		
            // InternalDsl.g:919:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:920:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:920:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:921:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_7=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getElasticGETAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getElasticGETAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:945:3: ( (lv_value_9_0= ruleSelectStatement ) )
            // InternalDsl.g:946:4: (lv_value_9_0= ruleSelectStatement )
            {
            // InternalDsl.g:946:4: (lv_value_9_0= ruleSelectStatement )
            // InternalDsl.g:947:5: lv_value_9_0= ruleSelectStatement
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

            otherlv_10=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_10, grammarAccess.getElasticGETAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_11, grammarAccess.getElasticGETAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:972:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:973:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:973:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:974:5: lv_condition_12_0= ruleExpression
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
    // InternalDsl.g:995:1: entryRuleExecJava returns [EObject current=null] : iv_ruleExecJava= ruleExecJava EOF ;
    public final EObject entryRuleExecJava() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExecJava = null;


        try {
            // InternalDsl.g:995:49: (iv_ruleExecJava= ruleExecJava EOF )
            // InternalDsl.g:996:2: iv_ruleExecJava= ruleExecJava EOF
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
    // InternalDsl.g:1002:1: ruleExecJava returns [EObject current=null] : (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:1008:2: ( (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) ) )
            // InternalDsl.g:1009:2: (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1009:2: (otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) ) )
            // InternalDsl.g:1010:3: otherlv_0= 'java' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'using' ( (lv_classFqn_4_0= RULE_STRING ) ) otherlv_5= 'name-sake-db' ( (lv_dbSrc_6_0= RULE_STRING ) ) otherlv_7= '{' ( (lv_value_8_0= RULE_STRING ) ) otherlv_9= '}' otherlv_10= 'on-condition' ( (lv_condition_11_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,29,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getExecJavaAccess().getJavaKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getExecJavaAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1018:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1019:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1019:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1020:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_3=(Token)match(input,24,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getExecJavaAccess().getUsingKeyword_3());
            		
            // InternalDsl.g:1040:3: ( (lv_classFqn_4_0= RULE_STRING ) )
            // InternalDsl.g:1041:4: (lv_classFqn_4_0= RULE_STRING )
            {
            // InternalDsl.g:1041:4: (lv_classFqn_4_0= RULE_STRING )
            // InternalDsl.g:1042:5: lv_classFqn_4_0= RULE_STRING
            {
            lv_classFqn_4_0=(Token)match(input,RULE_STRING,FOLLOW_22); 

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

            otherlv_5=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getExecJavaAccess().getNameSakeDbKeyword_5());
            		
            // InternalDsl.g:1062:3: ( (lv_dbSrc_6_0= RULE_STRING ) )
            // InternalDsl.g:1063:4: (lv_dbSrc_6_0= RULE_STRING )
            {
            // InternalDsl.g:1063:4: (lv_dbSrc_6_0= RULE_STRING )
            // InternalDsl.g:1064:5: lv_dbSrc_6_0= RULE_STRING
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
            		
            // InternalDsl.g:1084:3: ( (lv_value_8_0= RULE_STRING ) )
            // InternalDsl.g:1085:4: (lv_value_8_0= RULE_STRING )
            {
            // InternalDsl.g:1085:4: (lv_value_8_0= RULE_STRING )
            // InternalDsl.g:1086:5: lv_value_8_0= RULE_STRING
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

            otherlv_9=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_9, grammarAccess.getExecJavaAccess().getRightCurlyBracketKeyword_9());
            		
            otherlv_10=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_10, grammarAccess.getExecJavaAccess().getOnConditionKeyword_10());
            		
            // InternalDsl.g:1110:3: ( (lv_condition_11_0= ruleExpression ) )
            // InternalDsl.g:1111:4: (lv_condition_11_0= ruleExpression )
            {
            // InternalDsl.g:1111:4: (lv_condition_11_0= ruleExpression )
            // InternalDsl.g:1112:5: lv_condition_11_0= ruleExpression
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
    // InternalDsl.g:1133:1: entryRuleFirebaseDatabasePut returns [EObject current=null] : iv_ruleFirebaseDatabasePut= ruleFirebaseDatabasePut EOF ;
    public final EObject entryRuleFirebaseDatabasePut() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirebaseDatabasePut = null;


        try {
            // InternalDsl.g:1133:60: (iv_ruleFirebaseDatabasePut= ruleFirebaseDatabasePut EOF )
            // InternalDsl.g:1134:2: iv_ruleFirebaseDatabasePut= ruleFirebaseDatabasePut EOF
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
    // InternalDsl.g:1140:1: ruleFirebaseDatabasePut returns [EObject current=null] : (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:1146:2: ( (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:1147:2: (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1147:2: (otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:1148:3: otherlv_0= 'fbdp' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'from-source' ( (lv_dbSrc_10_0= RULE_STRING ) ) otherlv_11= 'with-class' ( (lv_classFqn_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,31,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFirebaseDatabasePutAccess().getFbdpKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFirebaseDatabasePutAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1156:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1157:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1157:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1158:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

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

            otherlv_3=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFirebaseDatabasePutAccess().getOnKeyword_3());
            		
            // InternalDsl.g:1178:3: ( (lv_url_4_0= RULE_STRING ) )
            // InternalDsl.g:1179:4: (lv_url_4_0= RULE_STRING )
            {
            // InternalDsl.g:1179:4: (lv_url_4_0= RULE_STRING )
            // InternalDsl.g:1180:5: lv_url_4_0= RULE_STRING
            {
            lv_url_4_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

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

            otherlv_5=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFirebaseDatabasePutAccess().getAuthByKeyword_5());
            		
            // InternalDsl.g:1200:3: ( (lv_fbjson_6_0= RULE_STRING ) )
            // InternalDsl.g:1201:4: (lv_fbjson_6_0= RULE_STRING )
            {
            // InternalDsl.g:1201:4: (lv_fbjson_6_0= RULE_STRING )
            // InternalDsl.g:1202:5: lv_fbjson_6_0= RULE_STRING
            {
            lv_fbjson_6_0=(Token)match(input,RULE_STRING,FOLLOW_25); 

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

            otherlv_7=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFirebaseDatabasePutAccess().getForGroupKeyword_7());
            		
            // InternalDsl.g:1222:3: ( (lv_groupPath_8_0= RULE_STRING ) )
            // InternalDsl.g:1223:4: (lv_groupPath_8_0= RULE_STRING )
            {
            // InternalDsl.g:1223:4: (lv_groupPath_8_0= RULE_STRING )
            // InternalDsl.g:1224:5: lv_groupPath_8_0= RULE_STRING
            {
            lv_groupPath_8_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

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

            otherlv_9=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFirebaseDatabasePutAccess().getFromSourceKeyword_9());
            		
            // InternalDsl.g:1244:3: ( (lv_dbSrc_10_0= RULE_STRING ) )
            // InternalDsl.g:1245:4: (lv_dbSrc_10_0= RULE_STRING )
            {
            // InternalDsl.g:1245:4: (lv_dbSrc_10_0= RULE_STRING )
            // InternalDsl.g:1246:5: lv_dbSrc_10_0= RULE_STRING
            {
            lv_dbSrc_10_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

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

            otherlv_11=(Token)match(input,36,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFirebaseDatabasePutAccess().getWithClassKeyword_11());
            		
            // InternalDsl.g:1266:3: ( (lv_classFqn_12_0= RULE_STRING ) )
            // InternalDsl.g:1267:4: (lv_classFqn_12_0= RULE_STRING )
            {
            // InternalDsl.g:1267:4: (lv_classFqn_12_0= RULE_STRING )
            // InternalDsl.g:1268:5: lv_classFqn_12_0= RULE_STRING
            {
            lv_classFqn_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_13=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getFirebaseDatabasePutAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getFirebaseDatabasePutAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:1292:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:1293:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:1293:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:1294:5: lv_value_15_0= RULE_STRING
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

            otherlv_16=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_16, grammarAccess.getFirebaseDatabasePutAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_17, grammarAccess.getFirebaseDatabasePutAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:1318:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:1319:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:1319:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:1320:5: lv_condition_18_0= ruleExpression
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
    // InternalDsl.g:1341:1: entryRuleFirebaseReactiveNotification returns [EObject current=null] : iv_ruleFirebaseReactiveNotification= ruleFirebaseReactiveNotification EOF ;
    public final EObject entryRuleFirebaseReactiveNotification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirebaseReactiveNotification = null;


        try {
            // InternalDsl.g:1341:69: (iv_ruleFirebaseReactiveNotification= ruleFirebaseReactiveNotification EOF )
            // InternalDsl.g:1342:2: iv_ruleFirebaseReactiveNotification= ruleFirebaseReactiveNotification EOF
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
    // InternalDsl.g:1348:1: ruleFirebaseReactiveNotification returns [EObject current=null] : (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:1354:2: ( (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:1355:2: (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1355:2: (otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:1356:3: otherlv_0= 'fbrn' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'auth-by' ( (lv_fbjson_6_0= RULE_STRING ) ) otherlv_7= 'for-group' ( (lv_groupPath_8_0= RULE_STRING ) ) otherlv_9= 'with-class' ( (lv_classFqn_10_0= RULE_STRING ) ) otherlv_11= 'using' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,37,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFirebaseReactiveNotificationAccess().getFbrnKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFirebaseReactiveNotificationAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1364:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1365:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1365:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1366:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

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

            otherlv_3=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFirebaseReactiveNotificationAccess().getOnKeyword_3());
            		
            // InternalDsl.g:1386:3: ( (lv_url_4_0= RULE_STRING ) )
            // InternalDsl.g:1387:4: (lv_url_4_0= RULE_STRING )
            {
            // InternalDsl.g:1387:4: (lv_url_4_0= RULE_STRING )
            // InternalDsl.g:1388:5: lv_url_4_0= RULE_STRING
            {
            lv_url_4_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

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

            otherlv_5=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFirebaseReactiveNotificationAccess().getAuthByKeyword_5());
            		
            // InternalDsl.g:1408:3: ( (lv_fbjson_6_0= RULE_STRING ) )
            // InternalDsl.g:1409:4: (lv_fbjson_6_0= RULE_STRING )
            {
            // InternalDsl.g:1409:4: (lv_fbjson_6_0= RULE_STRING )
            // InternalDsl.g:1410:5: lv_fbjson_6_0= RULE_STRING
            {
            lv_fbjson_6_0=(Token)match(input,RULE_STRING,FOLLOW_25); 

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

            otherlv_7=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFirebaseReactiveNotificationAccess().getForGroupKeyword_7());
            		
            // InternalDsl.g:1430:3: ( (lv_groupPath_8_0= RULE_STRING ) )
            // InternalDsl.g:1431:4: (lv_groupPath_8_0= RULE_STRING )
            {
            // InternalDsl.g:1431:4: (lv_groupPath_8_0= RULE_STRING )
            // InternalDsl.g:1432:5: lv_groupPath_8_0= RULE_STRING
            {
            lv_groupPath_8_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

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

            otherlv_9=(Token)match(input,36,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFirebaseReactiveNotificationAccess().getWithClassKeyword_9());
            		
            // InternalDsl.g:1452:3: ( (lv_classFqn_10_0= RULE_STRING ) )
            // InternalDsl.g:1453:4: (lv_classFqn_10_0= RULE_STRING )
            {
            // InternalDsl.g:1453:4: (lv_classFqn_10_0= RULE_STRING )
            // InternalDsl.g:1454:5: lv_classFqn_10_0= RULE_STRING
            {
            lv_classFqn_10_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_11=(Token)match(input,24,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFirebaseReactiveNotificationAccess().getUsingKeyword_11());
            		
            // InternalDsl.g:1474:3: ( (lv_dbSrc_12_0= RULE_STRING ) )
            // InternalDsl.g:1475:4: (lv_dbSrc_12_0= RULE_STRING )
            {
            // InternalDsl.g:1475:4: (lv_dbSrc_12_0= RULE_STRING )
            // InternalDsl.g:1476:5: lv_dbSrc_12_0= RULE_STRING
            {
            lv_dbSrc_12_0=(Token)match(input,RULE_STRING,FOLLOW_18); 

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

            otherlv_13=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_13, grammarAccess.getFirebaseReactiveNotificationAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:1496:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:1497:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:1497:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:1498:5: lv_condition_14_0= ruleExpression
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
    // InternalDsl.g:1519:1: entryRuleSmsLeadSms returns [EObject current=null] : iv_ruleSmsLeadSms= ruleSmsLeadSms EOF ;
    public final EObject entryRuleSmsLeadSms() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSmsLeadSms = null;


        try {
            // InternalDsl.g:1519:51: (iv_ruleSmsLeadSms= ruleSmsLeadSms EOF )
            // InternalDsl.g:1520:2: iv_ruleSmsLeadSms= ruleSmsLeadSms EOF
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
    // InternalDsl.g:1526:1: ruleSmsLeadSms returns [EObject current=null] : (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) ) ;
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
            // InternalDsl.g:1532:2: ( (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) ) )
            // InternalDsl.g:1533:2: (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:1533:2: (otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) ) )
            // InternalDsl.g:1534:3: otherlv_0= 'smsleadssms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_url_4_0= RULE_STRING ) ) otherlv_5= 'with-sender' ( (lv_sender_6_0= RULE_STRING ) ) otherlv_7= 'through-account' ( (lv_account_8_0= RULE_STRING ) ) otherlv_9= 'secured-by' ( (lv_privateKey_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_dbSrc_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) otherlv_19= 'do-dryrun-with' ( (lv_dryrunNumber_20_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,38,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getSmsLeadSmsAccess().getSmsleadssmsKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getSmsLeadSmsAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1542:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1543:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1543:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1544:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

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

            otherlv_3=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getSmsLeadSmsAccess().getOnKeyword_3());
            		
            // InternalDsl.g:1564:3: ( (lv_url_4_0= RULE_STRING ) )
            // InternalDsl.g:1565:4: (lv_url_4_0= RULE_STRING )
            {
            // InternalDsl.g:1565:4: (lv_url_4_0= RULE_STRING )
            // InternalDsl.g:1566:5: lv_url_4_0= RULE_STRING
            {
            lv_url_4_0=(Token)match(input,RULE_STRING,FOLLOW_28); 

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

            otherlv_5=(Token)match(input,39,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getSmsLeadSmsAccess().getWithSenderKeyword_5());
            		
            // InternalDsl.g:1586:3: ( (lv_sender_6_0= RULE_STRING ) )
            // InternalDsl.g:1587:4: (lv_sender_6_0= RULE_STRING )
            {
            // InternalDsl.g:1587:4: (lv_sender_6_0= RULE_STRING )
            // InternalDsl.g:1588:5: lv_sender_6_0= RULE_STRING
            {
            lv_sender_6_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

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

            otherlv_7=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getSmsLeadSmsAccess().getThroughAccountKeyword_7());
            		
            // InternalDsl.g:1608:3: ( (lv_account_8_0= RULE_STRING ) )
            // InternalDsl.g:1609:4: (lv_account_8_0= RULE_STRING )
            {
            // InternalDsl.g:1609:4: (lv_account_8_0= RULE_STRING )
            // InternalDsl.g:1610:5: lv_account_8_0= RULE_STRING
            {
            lv_account_8_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_9=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getSmsLeadSmsAccess().getSecuredByKeyword_9());
            		
            // InternalDsl.g:1630:3: ( (lv_privateKey_10_0= RULE_STRING ) )
            // InternalDsl.g:1631:4: (lv_privateKey_10_0= RULE_STRING )
            {
            // InternalDsl.g:1631:4: (lv_privateKey_10_0= RULE_STRING )
            // InternalDsl.g:1632:5: lv_privateKey_10_0= RULE_STRING
            {
            lv_privateKey_10_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

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

            otherlv_11=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getSmsLeadSmsAccess().getFromSourceKeyword_11());
            		
            // InternalDsl.g:1652:3: ( (lv_dbSrc_12_0= RULE_STRING ) )
            // InternalDsl.g:1653:4: (lv_dbSrc_12_0= RULE_STRING )
            {
            // InternalDsl.g:1653:4: (lv_dbSrc_12_0= RULE_STRING )
            // InternalDsl.g:1654:5: lv_dbSrc_12_0= RULE_STRING
            {
            lv_dbSrc_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_13=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getSmsLeadSmsAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getSmsLeadSmsAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:1678:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:1679:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:1679:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:1680:5: lv_value_15_0= RULE_STRING
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

            otherlv_16=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_16, grammarAccess.getSmsLeadSmsAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_17, grammarAccess.getSmsLeadSmsAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:1704:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:1705:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:1705:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:1706:5: lv_condition_18_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getSmsLeadSmsAccess().getConditionExpressionParserRuleCall_18_0());
            				
            pushFollow(FOLLOW_30);
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

            otherlv_19=(Token)match(input,41,FOLLOW_3); 

            			newLeafNode(otherlv_19, grammarAccess.getSmsLeadSmsAccess().getDoDryrunWithKeyword_19());
            		
            // InternalDsl.g:1727:3: ( (lv_dryrunNumber_20_0= RULE_STRING ) )
            // InternalDsl.g:1728:4: (lv_dryrunNumber_20_0= RULE_STRING )
            {
            // InternalDsl.g:1728:4: (lv_dryrunNumber_20_0= RULE_STRING )
            // InternalDsl.g:1729:5: lv_dryrunNumber_20_0= RULE_STRING
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
    // InternalDsl.g:1749:1: entryRuleAbort returns [EObject current=null] : iv_ruleAbort= ruleAbort EOF ;
    public final EObject entryRuleAbort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAbort = null;


        try {
            // InternalDsl.g:1749:46: (iv_ruleAbort= ruleAbort EOF )
            // InternalDsl.g:1750:2: iv_ruleAbort= ruleAbort EOF
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
    // InternalDsl.g:1756:1: ruleAbort returns [EObject current=null] : (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:1762:2: ( (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) ) )
            // InternalDsl.g:1763:2: (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1763:2: (otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) ) )
            // InternalDsl.g:1764:3: otherlv_0= 'abort' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' ( (lv_value_4_0= RULE_STRING ) ) otherlv_5= '}' otherlv_6= 'on-condition' ( (lv_condition_7_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,42,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getAbortAccess().getAbortKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getAbortAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1772:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1773:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1773:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1774:5: lv_name_2_0= RULE_STRING
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
            		
            // InternalDsl.g:1794:3: ( (lv_value_4_0= RULE_STRING ) )
            // InternalDsl.g:1795:4: (lv_value_4_0= RULE_STRING )
            {
            // InternalDsl.g:1795:4: (lv_value_4_0= RULE_STRING )
            // InternalDsl.g:1796:5: lv_value_4_0= RULE_STRING
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

            otherlv_5=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_5, grammarAccess.getAbortAccess().getRightCurlyBracketKeyword_5());
            		
            otherlv_6=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_6, grammarAccess.getAbortAccess().getOnConditionKeyword_6());
            		
            // InternalDsl.g:1820:3: ( (lv_condition_7_0= ruleExpression ) )
            // InternalDsl.g:1821:4: (lv_condition_7_0= ruleExpression )
            {
            // InternalDsl.g:1821:4: (lv_condition_7_0= ruleExpression )
            // InternalDsl.g:1822:5: lv_condition_7_0= ruleExpression
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
    // InternalDsl.g:1843:1: entryRuleGooglecontactSelectAll returns [EObject current=null] : iv_ruleGooglecontactSelectAll= ruleGooglecontactSelectAll EOF ;
    public final EObject entryRuleGooglecontactSelectAll() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGooglecontactSelectAll = null;


        try {
            // InternalDsl.g:1843:63: (iv_ruleGooglecontactSelectAll= ruleGooglecontactSelectAll EOF )
            // InternalDsl.g:1844:2: iv_ruleGooglecontactSelectAll= ruleGooglecontactSelectAll EOF
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
    // InternalDsl.g:1850:1: ruleGooglecontactSelectAll returns [EObject current=null] : (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:1856:2: ( (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) )
            // InternalDsl.g:1857:2: (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            {
            // InternalDsl.g:1857:2: (otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            // InternalDsl.g:1858:3: otherlv_0= 'gcontact-fetchall' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'to-target' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,43,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getGooglecontactSelectAllAccess().getGcontactFetchallKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGooglecontactSelectAllAccess().getAsKeyword_1());
            		
            // InternalDsl.g:1866:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:1867:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:1867:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:1868:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

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

            otherlv_3=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getGooglecontactSelectAllAccess().getThroughAccountKeyword_3());
            		
            // InternalDsl.g:1888:3: ( (lv_account_4_0= RULE_STRING ) )
            // InternalDsl.g:1889:4: (lv_account_4_0= RULE_STRING )
            {
            // InternalDsl.g:1889:4: (lv_account_4_0= RULE_STRING )
            // InternalDsl.g:1890:5: lv_account_4_0= RULE_STRING
            {
            lv_account_4_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_5=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getGooglecontactSelectAllAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:1910:3: ( (lv_privateKey_6_0= RULE_STRING ) )
            // InternalDsl.g:1911:4: (lv_privateKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:1911:4: (lv_privateKey_6_0= RULE_STRING )
            // InternalDsl.g:1912:5: lv_privateKey_6_0= RULE_STRING
            {
            lv_privateKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_7=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGooglecontactSelectAllAccess().getWithKeyKeyword_7());
            		
            // InternalDsl.g:1932:3: ( (lv_ptwelveFile_8_0= RULE_STRING ) )
            // InternalDsl.g:1933:4: (lv_ptwelveFile_8_0= RULE_STRING )
            {
            // InternalDsl.g:1933:4: (lv_ptwelveFile_8_0= RULE_STRING )
            // InternalDsl.g:1934:5: lv_ptwelveFile_8_0= RULE_STRING
            {
            lv_ptwelveFile_8_0=(Token)match(input,RULE_STRING,FOLLOW_31); 

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

            otherlv_9=(Token)match(input,44,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getGooglecontactSelectAllAccess().getForProjectKeyword_9());
            		
            // InternalDsl.g:1954:3: ( (lv_project_10_0= RULE_STRING ) )
            // InternalDsl.g:1955:4: (lv_project_10_0= RULE_STRING )
            {
            // InternalDsl.g:1955:4: (lv_project_10_0= RULE_STRING )
            // InternalDsl.g:1956:5: lv_project_10_0= RULE_STRING
            {
            lv_project_10_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

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

            otherlv_11=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getGooglecontactSelectAllAccess().getOnBehalfOfKeyword_11());
            		
            // InternalDsl.g:1976:3: ( (lv_impersonatedUser_12_0= RULE_STRING ) )
            // InternalDsl.g:1977:4: (lv_impersonatedUser_12_0= RULE_STRING )
            {
            // InternalDsl.g:1977:4: (lv_impersonatedUser_12_0= RULE_STRING )
            // InternalDsl.g:1978:5: lv_impersonatedUser_12_0= RULE_STRING
            {
            lv_impersonatedUser_12_0=(Token)match(input,RULE_STRING,FOLLOW_33); 

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

            otherlv_13=(Token)match(input,46,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getGooglecontactSelectAllAccess().getToTargetKeyword_13());
            		
            // InternalDsl.g:1998:3: ( (lv_dbSrc_14_0= RULE_STRING ) )
            // InternalDsl.g:1999:4: (lv_dbSrc_14_0= RULE_STRING )
            {
            // InternalDsl.g:1999:4: (lv_dbSrc_14_0= RULE_STRING )
            // InternalDsl.g:2000:5: lv_dbSrc_14_0= RULE_STRING
            {
            lv_dbSrc_14_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_15=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_15, grammarAccess.getGooglecontactSelectAllAccess().getUsingKeyword_15());
            		
            otherlv_16=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getGooglecontactSelectAllAccess().getLeftCurlyBracketKeyword_16());
            		
            // InternalDsl.g:2024:3: ( (lv_value_17_0= RULE_STRING ) )
            // InternalDsl.g:2025:4: (lv_value_17_0= RULE_STRING )
            {
            // InternalDsl.g:2025:4: (lv_value_17_0= RULE_STRING )
            // InternalDsl.g:2026:5: lv_value_17_0= RULE_STRING
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

            otherlv_18=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_18, grammarAccess.getGooglecontactSelectAllAccess().getRightCurlyBracketKeyword_18());
            		
            otherlv_19=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_19, grammarAccess.getGooglecontactSelectAllAccess().getOnConditionKeyword_19());
            		
            // InternalDsl.g:2050:3: ( (lv_condition_20_0= ruleExpression ) )
            // InternalDsl.g:2051:4: (lv_condition_20_0= ruleExpression )
            {
            // InternalDsl.g:2051:4: (lv_condition_20_0= ruleExpression )
            // InternalDsl.g:2052:5: lv_condition_20_0= ruleExpression
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
    // InternalDsl.g:2073:1: entryRuleSendMail returns [EObject current=null] : iv_ruleSendMail= ruleSendMail EOF ;
    public final EObject entryRuleSendMail() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSendMail = null;


        try {
            // InternalDsl.g:2073:49: (iv_ruleSendMail= ruleSendMail EOF )
            // InternalDsl.g:2074:2: iv_ruleSendMail= ruleSendMail EOF
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
    // InternalDsl.g:2080:1: ruleSendMail returns [EObject current=null] : (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) ) ;
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
            // InternalDsl.g:2086:2: ( (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) ) )
            // InternalDsl.g:2087:2: (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:2087:2: (otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) ) )
            // InternalDsl.g:2088:3: otherlv_0= 'sendmail' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_privateKey_4_0= RULE_STRING ) ) otherlv_5= 'on-behalf-of' ( (lv_impersonatedUser_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_dbSrc_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) otherlv_15= 'do-dryrun-with' ( (lv_dryrunMail_16_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,47,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getSendMailAccess().getSendmailKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getSendMailAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2096:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2097:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2097:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2098:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getSendMailAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:2118:3: ( (lv_privateKey_4_0= RULE_STRING ) )
            // InternalDsl.g:2119:4: (lv_privateKey_4_0= RULE_STRING )
            {
            // InternalDsl.g:2119:4: (lv_privateKey_4_0= RULE_STRING )
            // InternalDsl.g:2120:5: lv_privateKey_4_0= RULE_STRING
            {
            lv_privateKey_4_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

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

            otherlv_5=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getSendMailAccess().getOnBehalfOfKeyword_5());
            		
            // InternalDsl.g:2140:3: ( (lv_impersonatedUser_6_0= RULE_STRING ) )
            // InternalDsl.g:2141:4: (lv_impersonatedUser_6_0= RULE_STRING )
            {
            // InternalDsl.g:2141:4: (lv_impersonatedUser_6_0= RULE_STRING )
            // InternalDsl.g:2142:5: lv_impersonatedUser_6_0= RULE_STRING
            {
            lv_impersonatedUser_6_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

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

            otherlv_7=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getSendMailAccess().getFromSourceKeyword_7());
            		
            // InternalDsl.g:2162:3: ( (lv_dbSrc_8_0= RULE_STRING ) )
            // InternalDsl.g:2163:4: (lv_dbSrc_8_0= RULE_STRING )
            {
            // InternalDsl.g:2163:4: (lv_dbSrc_8_0= RULE_STRING )
            // InternalDsl.g:2164:5: lv_dbSrc_8_0= RULE_STRING
            {
            lv_dbSrc_8_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_9=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getSendMailAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getSendMailAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:2188:3: ( (lv_value_11_0= RULE_STRING ) )
            // InternalDsl.g:2189:4: (lv_value_11_0= RULE_STRING )
            {
            // InternalDsl.g:2189:4: (lv_value_11_0= RULE_STRING )
            // InternalDsl.g:2190:5: lv_value_11_0= RULE_STRING
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

            otherlv_12=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_12, grammarAccess.getSendMailAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_13, grammarAccess.getSendMailAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:2214:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:2215:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:2215:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:2216:5: lv_condition_14_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getSendMailAccess().getConditionExpressionParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_30);
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

            otherlv_15=(Token)match(input,41,FOLLOW_3); 

            			newLeafNode(otherlv_15, grammarAccess.getSendMailAccess().getDoDryrunWithKeyword_15());
            		
            // InternalDsl.g:2237:3: ( (lv_dryrunMail_16_0= RULE_STRING ) )
            // InternalDsl.g:2238:4: (lv_dryrunMail_16_0= RULE_STRING )
            {
            // InternalDsl.g:2238:4: (lv_dryrunMail_16_0= RULE_STRING )
            // InternalDsl.g:2239:5: lv_dryrunMail_16_0= RULE_STRING
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
    // InternalDsl.g:2259:1: entryRuleGooglecontactPUT returns [EObject current=null] : iv_ruleGooglecontactPUT= ruleGooglecontactPUT EOF ;
    public final EObject entryRuleGooglecontactPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGooglecontactPUT = null;


        try {
            // InternalDsl.g:2259:57: (iv_ruleGooglecontactPUT= ruleGooglecontactPUT EOF )
            // InternalDsl.g:2260:2: iv_ruleGooglecontactPUT= ruleGooglecontactPUT EOF
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
    // InternalDsl.g:2266:1: ruleGooglecontactPUT returns [EObject current=null] : (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:2272:2: ( (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) )
            // InternalDsl.g:2273:2: (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2273:2: (otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            // InternalDsl.g:2274:3: otherlv_0= 'gcontact' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,48,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getGooglecontactPUTAccess().getGcontactKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGooglecontactPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2282:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2283:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2283:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2284:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

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

            otherlv_3=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getGooglecontactPUTAccess().getThroughAccountKeyword_3());
            		
            // InternalDsl.g:2304:3: ( (lv_account_4_0= RULE_STRING ) )
            // InternalDsl.g:2305:4: (lv_account_4_0= RULE_STRING )
            {
            // InternalDsl.g:2305:4: (lv_account_4_0= RULE_STRING )
            // InternalDsl.g:2306:5: lv_account_4_0= RULE_STRING
            {
            lv_account_4_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_5=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getGooglecontactPUTAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:2326:3: ( (lv_privateKey_6_0= RULE_STRING ) )
            // InternalDsl.g:2327:4: (lv_privateKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:2327:4: (lv_privateKey_6_0= RULE_STRING )
            // InternalDsl.g:2328:5: lv_privateKey_6_0= RULE_STRING
            {
            lv_privateKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_7=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGooglecontactPUTAccess().getWithKeyKeyword_7());
            		
            // InternalDsl.g:2348:3: ( (lv_ptwelveFile_8_0= RULE_STRING ) )
            // InternalDsl.g:2349:4: (lv_ptwelveFile_8_0= RULE_STRING )
            {
            // InternalDsl.g:2349:4: (lv_ptwelveFile_8_0= RULE_STRING )
            // InternalDsl.g:2350:5: lv_ptwelveFile_8_0= RULE_STRING
            {
            lv_ptwelveFile_8_0=(Token)match(input,RULE_STRING,FOLLOW_31); 

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

            otherlv_9=(Token)match(input,44,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getGooglecontactPUTAccess().getForProjectKeyword_9());
            		
            // InternalDsl.g:2370:3: ( (lv_project_10_0= RULE_STRING ) )
            // InternalDsl.g:2371:4: (lv_project_10_0= RULE_STRING )
            {
            // InternalDsl.g:2371:4: (lv_project_10_0= RULE_STRING )
            // InternalDsl.g:2372:5: lv_project_10_0= RULE_STRING
            {
            lv_project_10_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

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

            otherlv_11=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getGooglecontactPUTAccess().getOnBehalfOfKeyword_11());
            		
            // InternalDsl.g:2392:3: ( (lv_impersonatedUser_12_0= RULE_STRING ) )
            // InternalDsl.g:2393:4: (lv_impersonatedUser_12_0= RULE_STRING )
            {
            // InternalDsl.g:2393:4: (lv_impersonatedUser_12_0= RULE_STRING )
            // InternalDsl.g:2394:5: lv_impersonatedUser_12_0= RULE_STRING
            {
            lv_impersonatedUser_12_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

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

            otherlv_13=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getGooglecontactPUTAccess().getFromSourceKeyword_13());
            		
            // InternalDsl.g:2414:3: ( (lv_dbSrc_14_0= RULE_STRING ) )
            // InternalDsl.g:2415:4: (lv_dbSrc_14_0= RULE_STRING )
            {
            // InternalDsl.g:2415:4: (lv_dbSrc_14_0= RULE_STRING )
            // InternalDsl.g:2416:5: lv_dbSrc_14_0= RULE_STRING
            {
            lv_dbSrc_14_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_15=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_15, grammarAccess.getGooglecontactPUTAccess().getUsingKeyword_15());
            		
            otherlv_16=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getGooglecontactPUTAccess().getLeftCurlyBracketKeyword_16());
            		
            // InternalDsl.g:2440:3: ( (lv_value_17_0= RULE_STRING ) )
            // InternalDsl.g:2441:4: (lv_value_17_0= RULE_STRING )
            {
            // InternalDsl.g:2441:4: (lv_value_17_0= RULE_STRING )
            // InternalDsl.g:2442:5: lv_value_17_0= RULE_STRING
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

            otherlv_18=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_18, grammarAccess.getGooglecontactPUTAccess().getRightCurlyBracketKeyword_18());
            		
            otherlv_19=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_19, grammarAccess.getGooglecontactPUTAccess().getOnConditionKeyword_19());
            		
            // InternalDsl.g:2466:3: ( (lv_condition_20_0= ruleExpression ) )
            // InternalDsl.g:2467:4: (lv_condition_20_0= ruleExpression )
            {
            // InternalDsl.g:2467:4: (lv_condition_20_0= ruleExpression )
            // InternalDsl.g:2468:5: lv_condition_20_0= ruleExpression
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
    // InternalDsl.g:2489:1: entryRuleGooglecalPUT returns [EObject current=null] : iv_ruleGooglecalPUT= ruleGooglecalPUT EOF ;
    public final EObject entryRuleGooglecalPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGooglecalPUT = null;


        try {
            // InternalDsl.g:2489:53: (iv_ruleGooglecalPUT= ruleGooglecalPUT EOF )
            // InternalDsl.g:2490:2: iv_ruleGooglecalPUT= ruleGooglecalPUT EOF
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
    // InternalDsl.g:2496:1: ruleGooglecalPUT returns [EObject current=null] : (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:2502:2: ( (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) ) )
            // InternalDsl.g:2503:2: (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2503:2: (otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) ) )
            // InternalDsl.g:2504:3: otherlv_0= 'gcalendar' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'through-account' ( (lv_account_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_privateKey_6_0= RULE_STRING ) ) otherlv_7= 'with-key' ( (lv_ptwelveFile_8_0= RULE_STRING ) ) otherlv_9= 'for-project' ( (lv_project_10_0= RULE_STRING ) ) otherlv_11= 'on-behalf-of' ( (lv_impersonatedUser_12_0= RULE_STRING ) ) otherlv_13= 'from-source' ( (lv_dbSrc_14_0= RULE_STRING ) ) otherlv_15= 'using' otherlv_16= '{' ( (lv_value_17_0= RULE_STRING ) ) otherlv_18= '}' otherlv_19= 'on-condition' ( (lv_condition_20_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,49,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getGooglecalPUTAccess().getGcalendarKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGooglecalPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2512:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2513:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2513:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2514:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

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

            otherlv_3=(Token)match(input,40,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getGooglecalPUTAccess().getThroughAccountKeyword_3());
            		
            // InternalDsl.g:2534:3: ( (lv_account_4_0= RULE_STRING ) )
            // InternalDsl.g:2535:4: (lv_account_4_0= RULE_STRING )
            {
            // InternalDsl.g:2535:4: (lv_account_4_0= RULE_STRING )
            // InternalDsl.g:2536:5: lv_account_4_0= RULE_STRING
            {
            lv_account_4_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_5=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getGooglecalPUTAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:2556:3: ( (lv_privateKey_6_0= RULE_STRING ) )
            // InternalDsl.g:2557:4: (lv_privateKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:2557:4: (lv_privateKey_6_0= RULE_STRING )
            // InternalDsl.g:2558:5: lv_privateKey_6_0= RULE_STRING
            {
            lv_privateKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_7=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGooglecalPUTAccess().getWithKeyKeyword_7());
            		
            // InternalDsl.g:2578:3: ( (lv_ptwelveFile_8_0= RULE_STRING ) )
            // InternalDsl.g:2579:4: (lv_ptwelveFile_8_0= RULE_STRING )
            {
            // InternalDsl.g:2579:4: (lv_ptwelveFile_8_0= RULE_STRING )
            // InternalDsl.g:2580:5: lv_ptwelveFile_8_0= RULE_STRING
            {
            lv_ptwelveFile_8_0=(Token)match(input,RULE_STRING,FOLLOW_31); 

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

            otherlv_9=(Token)match(input,44,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getGooglecalPUTAccess().getForProjectKeyword_9());
            		
            // InternalDsl.g:2600:3: ( (lv_project_10_0= RULE_STRING ) )
            // InternalDsl.g:2601:4: (lv_project_10_0= RULE_STRING )
            {
            // InternalDsl.g:2601:4: (lv_project_10_0= RULE_STRING )
            // InternalDsl.g:2602:5: lv_project_10_0= RULE_STRING
            {
            lv_project_10_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

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

            otherlv_11=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getGooglecalPUTAccess().getOnBehalfOfKeyword_11());
            		
            // InternalDsl.g:2622:3: ( (lv_impersonatedUser_12_0= RULE_STRING ) )
            // InternalDsl.g:2623:4: (lv_impersonatedUser_12_0= RULE_STRING )
            {
            // InternalDsl.g:2623:4: (lv_impersonatedUser_12_0= RULE_STRING )
            // InternalDsl.g:2624:5: lv_impersonatedUser_12_0= RULE_STRING
            {
            lv_impersonatedUser_12_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

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

            otherlv_13=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getGooglecalPUTAccess().getFromSourceKeyword_13());
            		
            // InternalDsl.g:2644:3: ( (lv_dbSrc_14_0= RULE_STRING ) )
            // InternalDsl.g:2645:4: (lv_dbSrc_14_0= RULE_STRING )
            {
            // InternalDsl.g:2645:4: (lv_dbSrc_14_0= RULE_STRING )
            // InternalDsl.g:2646:5: lv_dbSrc_14_0= RULE_STRING
            {
            lv_dbSrc_14_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_15=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_15, grammarAccess.getGooglecalPUTAccess().getUsingKeyword_15());
            		
            otherlv_16=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getGooglecalPUTAccess().getLeftCurlyBracketKeyword_16());
            		
            // InternalDsl.g:2670:3: ( (lv_value_17_0= RULE_STRING ) )
            // InternalDsl.g:2671:4: (lv_value_17_0= RULE_STRING )
            {
            // InternalDsl.g:2671:4: (lv_value_17_0= RULE_STRING )
            // InternalDsl.g:2672:5: lv_value_17_0= RULE_STRING
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

            otherlv_18=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_18, grammarAccess.getGooglecalPUTAccess().getRightCurlyBracketKeyword_18());
            		
            otherlv_19=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_19, grammarAccess.getGooglecalPUTAccess().getOnConditionKeyword_19());
            		
            // InternalDsl.g:2696:3: ( (lv_condition_20_0= ruleExpression ) )
            // InternalDsl.g:2697:4: (lv_condition_20_0= ruleExpression )
            {
            // InternalDsl.g:2697:4: (lv_condition_20_0= ruleExpression )
            // InternalDsl.g:2698:5: lv_condition_20_0= ruleExpression
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
    // InternalDsl.g:2719:1: entryRuleFBCLead returns [EObject current=null] : iv_ruleFBCLead= ruleFBCLead EOF ;
    public final EObject entryRuleFBCLead() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFBCLead = null;


        try {
            // InternalDsl.g:2719:48: (iv_ruleFBCLead= ruleFBCLead EOF )
            // InternalDsl.g:2720:2: iv_ruleFBCLead= ruleFBCLead EOF
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
    // InternalDsl.g:2726:1: ruleFBCLead returns [EObject current=null] : (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:2732:2: ( (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:2733:2: (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2733:2: (otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:2734:3: otherlv_0= 'fblc' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-campaign' ( (lv_campaignId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,50,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFBCLeadAccess().getFblcKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFBCLeadAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2742:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2743:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2743:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2744:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFBCLeadAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:2764:3: ( (lv_accessToken_4_0= RULE_STRING ) )
            // InternalDsl.g:2765:4: (lv_accessToken_4_0= RULE_STRING )
            {
            // InternalDsl.g:2765:4: (lv_accessToken_4_0= RULE_STRING )
            // InternalDsl.g:2766:5: lv_accessToken_4_0= RULE_STRING
            {
            lv_accessToken_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFBCLeadAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:2786:3: ( (lv_appSecret_6_0= RULE_STRING ) )
            // InternalDsl.g:2787:4: (lv_appSecret_6_0= RULE_STRING )
            {
            // InternalDsl.g:2787:4: (lv_appSecret_6_0= RULE_STRING )
            // InternalDsl.g:2788:5: lv_appSecret_6_0= RULE_STRING
            {
            lv_appSecret_6_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

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

            otherlv_7=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFBCLeadAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:2808:3: ( (lv_accountId_8_0= RULE_STRING ) )
            // InternalDsl.g:2809:4: (lv_accountId_8_0= RULE_STRING )
            {
            // InternalDsl.g:2809:4: (lv_accountId_8_0= RULE_STRING )
            // InternalDsl.g:2810:5: lv_accountId_8_0= RULE_STRING
            {
            lv_accountId_8_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

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

            otherlv_9=(Token)match(input,22,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFBCLeadAccess().getForCampaignKeyword_9());
            		
            // InternalDsl.g:2830:3: ( (lv_campaignId_10_0= RULE_STRING ) )
            // InternalDsl.g:2831:4: (lv_campaignId_10_0= RULE_STRING )
            {
            // InternalDsl.g:2831:4: (lv_campaignId_10_0= RULE_STRING )
            // InternalDsl.g:2832:5: lv_campaignId_10_0= RULE_STRING
            {
            lv_campaignId_10_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

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

            otherlv_11=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFBCLeadAccess().getIntoKeyword_11());
            		
            // InternalDsl.g:2852:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:2853:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:2853:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:2854:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_13=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getFBCLeadAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getFBCLeadAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:2878:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:2879:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:2879:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:2880:5: lv_value_15_0= RULE_STRING
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

            otherlv_16=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_16, grammarAccess.getFBCLeadAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_17, grammarAccess.getFBCLeadAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:2904:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:2905:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:2905:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:2906:5: lv_condition_18_0= ruleExpression
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
    // InternalDsl.g:2927:1: entryRuleFBFormDownload returns [EObject current=null] : iv_ruleFBFormDownload= ruleFBFormDownload EOF ;
    public final EObject entryRuleFBFormDownload() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFBFormDownload = null;


        try {
            // InternalDsl.g:2927:55: (iv_ruleFBFormDownload= ruleFBFormDownload EOF )
            // InternalDsl.g:2928:2: iv_ruleFBFormDownload= ruleFBFormDownload EOF
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
    // InternalDsl.g:2934:1: ruleFBFormDownload returns [EObject current=null] : (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:2940:2: ( (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:2941:2: (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:2941:2: (otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:2942:3: otherlv_0= 'fbfd' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_accessToken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_appSecret_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_accountId_8_0= RULE_STRING ) ) otherlv_9= 'for-form' ( (lv_formId_10_0= RULE_STRING ) ) otherlv_11= 'into' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= RULE_STRING ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,51,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFBFormDownloadAccess().getFbfdKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFBFormDownloadAccess().getAsKeyword_1());
            		
            // InternalDsl.g:2950:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:2951:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:2951:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:2952:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFBFormDownloadAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:2972:3: ( (lv_accessToken_4_0= RULE_STRING ) )
            // InternalDsl.g:2973:4: (lv_accessToken_4_0= RULE_STRING )
            {
            // InternalDsl.g:2973:4: (lv_accessToken_4_0= RULE_STRING )
            // InternalDsl.g:2974:5: lv_accessToken_4_0= RULE_STRING
            {
            lv_accessToken_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getFBFormDownloadAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:2994:3: ( (lv_appSecret_6_0= RULE_STRING ) )
            // InternalDsl.g:2995:4: (lv_appSecret_6_0= RULE_STRING )
            {
            // InternalDsl.g:2995:4: (lv_appSecret_6_0= RULE_STRING )
            // InternalDsl.g:2996:5: lv_appSecret_6_0= RULE_STRING
            {
            lv_appSecret_6_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

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

            otherlv_7=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getFBFormDownloadAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:3016:3: ( (lv_accountId_8_0= RULE_STRING ) )
            // InternalDsl.g:3017:4: (lv_accountId_8_0= RULE_STRING )
            {
            // InternalDsl.g:3017:4: (lv_accountId_8_0= RULE_STRING )
            // InternalDsl.g:3018:5: lv_accountId_8_0= RULE_STRING
            {
            lv_accountId_8_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

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

            otherlv_9=(Token)match(input,52,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getFBFormDownloadAccess().getForFormKeyword_9());
            		
            // InternalDsl.g:3038:3: ( (lv_formId_10_0= RULE_STRING ) )
            // InternalDsl.g:3039:4: (lv_formId_10_0= RULE_STRING )
            {
            // InternalDsl.g:3039:4: (lv_formId_10_0= RULE_STRING )
            // InternalDsl.g:3040:5: lv_formId_10_0= RULE_STRING
            {
            lv_formId_10_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

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

            otherlv_11=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getFBFormDownloadAccess().getIntoKeyword_11());
            		
            // InternalDsl.g:3060:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:3061:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:3061:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:3062:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_13=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getFBFormDownloadAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getFBFormDownloadAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:3086:3: ( (lv_value_15_0= RULE_STRING ) )
            // InternalDsl.g:3087:4: (lv_value_15_0= RULE_STRING )
            {
            // InternalDsl.g:3087:4: (lv_value_15_0= RULE_STRING )
            // InternalDsl.g:3088:5: lv_value_15_0= RULE_STRING
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

            otherlv_16=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_16, grammarAccess.getFBFormDownloadAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_17, grammarAccess.getFBFormDownloadAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:3112:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:3113:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:3113:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:3114:5: lv_condition_18_0= ruleExpression
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
    // InternalDsl.g:3135:1: entryRuleDropfile returns [EObject current=null] : iv_ruleDropfile= ruleDropfile EOF ;
    public final EObject entryRuleDropfile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDropfile = null;


        try {
            // InternalDsl.g:3135:49: (iv_ruleDropfile= ruleDropfile EOF )
            // InternalDsl.g:3136:2: iv_ruleDropfile= ruleDropfile EOF
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
    // InternalDsl.g:3142:1: ruleDropfile returns [EObject current=null] : (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:3148:2: ( (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) ) )
            // InternalDsl.g:3149:2: (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3149:2: (otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) ) )
            // InternalDsl.g:3150:3: otherlv_0= 'dropfile' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-path' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on-condition' ( (lv_condition_6_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,53,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getDropfileAccess().getDropfileKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getDropfileAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3158:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3159:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3159:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3160:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

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

            otherlv_3=(Token)match(input,54,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getDropfileAccess().getInPathKeyword_3());
            		
            // InternalDsl.g:3180:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:3181:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:3181:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:3182:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_18); 

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

            otherlv_5=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_5, grammarAccess.getDropfileAccess().getOnConditionKeyword_5());
            		
            // InternalDsl.g:3202:3: ( (lv_condition_6_0= ruleExpression ) )
            // InternalDsl.g:3203:4: (lv_condition_6_0= ruleExpression )
            {
            // InternalDsl.g:3203:4: (lv_condition_6_0= ruleExpression )
            // InternalDsl.g:3204:5: lv_condition_6_0= ruleExpression
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
    // InternalDsl.g:3225:1: entryRuleDoozle returns [EObject current=null] : iv_ruleDoozle= ruleDoozle EOF ;
    public final EObject entryRuleDoozle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoozle = null;


        try {
            // InternalDsl.g:3225:47: (iv_ruleDoozle= ruleDoozle EOF )
            // InternalDsl.g:3226:2: iv_ruleDoozle= ruleDoozle EOF
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
    // InternalDsl.g:3232:1: ruleDoozle returns [EObject current=null] : (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:3238:2: ( (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:3239:2: (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3239:2: (otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:3240:3: otherlv_0= 'doozle' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'in-table' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_on_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleCreateStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,55,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getDoozleAccess().getDoozleKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getDoozleAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3248:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3249:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3249:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3250:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_36); 

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

            otherlv_3=(Token)match(input,56,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getDoozleAccess().getInTableKeyword_3());
            		
            // InternalDsl.g:3270:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:3271:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:3271:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:3272:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

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

            otherlv_5=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getDoozleAccess().getOnKeyword_5());
            		
            // InternalDsl.g:3292:3: ( (lv_on_6_0= RULE_STRING ) )
            // InternalDsl.g:3293:4: (lv_on_6_0= RULE_STRING )
            {
            // InternalDsl.g:3293:4: (lv_on_6_0= RULE_STRING )
            // InternalDsl.g:3294:5: lv_on_6_0= RULE_STRING
            {
            lv_on_6_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_7=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getDoozleAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getDoozleAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:3318:3: ( (lv_value_9_0= ruleCreateStatement ) )
            // InternalDsl.g:3319:4: (lv_value_9_0= ruleCreateStatement )
            {
            // InternalDsl.g:3319:4: (lv_value_9_0= ruleCreateStatement )
            // InternalDsl.g:3320:5: lv_value_9_0= ruleCreateStatement
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

            otherlv_10=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_10, grammarAccess.getDoozleAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_11, grammarAccess.getDoozleAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:3345:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:3346:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:3346:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:3347:5: lv_condition_12_0= ruleExpression
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
    // InternalDsl.g:3368:1: entryRuleRest returns [EObject current=null] : iv_ruleRest= ruleRest EOF ;
    public final EObject entryRuleRest() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRest = null;


        try {
            // InternalDsl.g:3368:45: (iv_ruleRest= ruleRest EOF )
            // InternalDsl.g:3369:2: iv_ruleRest= ruleRest EOF
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
    // InternalDsl.g:3375:1: ruleRest returns [EObject current=null] : (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:3381:2: ( (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) ) )
            // InternalDsl.g:3382:2: (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3382:2: (otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) ) )
            // InternalDsl.g:3383:3: otherlv_0= 'rest' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-url' ( (lv_url_6_0= RULE_STRING ) ) otherlv_7= 'and-method-as' ( (lv_method_8_0= RULE_STRING ) ) otherlv_9= '{' otherlv_10= 'from' ( (lv_resourcedatafrom_11_0= RULE_STRING ) ) otherlv_12= 'update-url-with' otherlv_13= '{' ( (lv_urldata_14_0= ruleSelectStatement ) ) otherlv_15= '}' otherlv_16= 'from' ( (lv_headerdatafrom_17_0= RULE_STRING ) ) otherlv_18= 'update-header-with' otherlv_19= '{' ( (lv_headerdata_20_0= ruleSelectStatement ) ) otherlv_21= '}' otherlv_22= 'from' ( (lv_postdatafrom_23_0= RULE_STRING ) ) otherlv_24= 'update-body-with' otherlv_25= '{' otherlv_26= 'parent' otherlv_27= 'as' ( (lv_parentName_28_0= RULE_STRING ) ) ( (lv_parentdata_29_0= ruleSelectStatement ) ) ( (lv_parts_30_0= ruleRestPart ) )* otherlv_31= '}' otherlv_32= 'into' ( (lv_ackdatato_33_0= RULE_STRING ) ) otherlv_34= 'store-ack-at' otherlv_35= '{' ( (lv_ackdata_36_0= ruleSelectStatement ) ) otherlv_37= '}' otherlv_38= '}' otherlv_39= 'on-condition' ( (lv_condition_40_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,57,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getRestAccess().getRestKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getRestAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3391:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3392:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3392:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3393:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getRestAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:3413:3: ( (lv_authtoken_4_0= RULE_STRING ) )
            // InternalDsl.g:3414:4: (lv_authtoken_4_0= RULE_STRING )
            {
            // InternalDsl.g:3414:4: (lv_authtoken_4_0= RULE_STRING )
            // InternalDsl.g:3415:5: lv_authtoken_4_0= RULE_STRING
            {
            lv_authtoken_4_0=(Token)match(input,RULE_STRING,FOLLOW_37); 

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

            otherlv_5=(Token)match(input,58,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getRestAccess().getWithUrlKeyword_5());
            		
            // InternalDsl.g:3435:3: ( (lv_url_6_0= RULE_STRING ) )
            // InternalDsl.g:3436:4: (lv_url_6_0= RULE_STRING )
            {
            // InternalDsl.g:3436:4: (lv_url_6_0= RULE_STRING )
            // InternalDsl.g:3437:5: lv_url_6_0= RULE_STRING
            {
            lv_url_6_0=(Token)match(input,RULE_STRING,FOLLOW_38); 

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

            otherlv_7=(Token)match(input,59,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getRestAccess().getAndMethodAsKeyword_7());
            		
            // InternalDsl.g:3457:3: ( (lv_method_8_0= RULE_STRING ) )
            // InternalDsl.g:3458:4: (lv_method_8_0= RULE_STRING )
            {
            // InternalDsl.g:3458:4: (lv_method_8_0= RULE_STRING )
            // InternalDsl.g:3459:5: lv_method_8_0= RULE_STRING
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

            otherlv_9=(Token)match(input,12,FOLLOW_20); 

            			newLeafNode(otherlv_9, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_9());
            		
            otherlv_10=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getRestAccess().getFromKeyword_10());
            		
            // InternalDsl.g:3483:3: ( (lv_resourcedatafrom_11_0= RULE_STRING ) )
            // InternalDsl.g:3484:4: (lv_resourcedatafrom_11_0= RULE_STRING )
            {
            // InternalDsl.g:3484:4: (lv_resourcedatafrom_11_0= RULE_STRING )
            // InternalDsl.g:3485:5: lv_resourcedatafrom_11_0= RULE_STRING
            {
            lv_resourcedatafrom_11_0=(Token)match(input,RULE_STRING,FOLLOW_39); 

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

            otherlv_12=(Token)match(input,60,FOLLOW_4); 

            			newLeafNode(otherlv_12, grammarAccess.getRestAccess().getUpdateUrlWithKeyword_12());
            		
            otherlv_13=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_13, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_13());
            		
            // InternalDsl.g:3509:3: ( (lv_urldata_14_0= ruleSelectStatement ) )
            // InternalDsl.g:3510:4: (lv_urldata_14_0= ruleSelectStatement )
            {
            // InternalDsl.g:3510:4: (lv_urldata_14_0= ruleSelectStatement )
            // InternalDsl.g:3511:5: lv_urldata_14_0= ruleSelectStatement
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

            otherlv_15=(Token)match(input,13,FOLLOW_20); 

            			newLeafNode(otherlv_15, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_15());
            		
            otherlv_16=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_16, grammarAccess.getRestAccess().getFromKeyword_16());
            		
            // InternalDsl.g:3536:3: ( (lv_headerdatafrom_17_0= RULE_STRING ) )
            // InternalDsl.g:3537:4: (lv_headerdatafrom_17_0= RULE_STRING )
            {
            // InternalDsl.g:3537:4: (lv_headerdatafrom_17_0= RULE_STRING )
            // InternalDsl.g:3538:5: lv_headerdatafrom_17_0= RULE_STRING
            {
            lv_headerdatafrom_17_0=(Token)match(input,RULE_STRING,FOLLOW_40); 

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

            otherlv_18=(Token)match(input,61,FOLLOW_4); 

            			newLeafNode(otherlv_18, grammarAccess.getRestAccess().getUpdateHeaderWithKeyword_18());
            		
            otherlv_19=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_19, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_19());
            		
            // InternalDsl.g:3562:3: ( (lv_headerdata_20_0= ruleSelectStatement ) )
            // InternalDsl.g:3563:4: (lv_headerdata_20_0= ruleSelectStatement )
            {
            // InternalDsl.g:3563:4: (lv_headerdata_20_0= ruleSelectStatement )
            // InternalDsl.g:3564:5: lv_headerdata_20_0= ruleSelectStatement
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

            otherlv_21=(Token)match(input,13,FOLLOW_20); 

            			newLeafNode(otherlv_21, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_21());
            		
            otherlv_22=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_22, grammarAccess.getRestAccess().getFromKeyword_22());
            		
            // InternalDsl.g:3589:3: ( (lv_postdatafrom_23_0= RULE_STRING ) )
            // InternalDsl.g:3590:4: (lv_postdatafrom_23_0= RULE_STRING )
            {
            // InternalDsl.g:3590:4: (lv_postdatafrom_23_0= RULE_STRING )
            // InternalDsl.g:3591:5: lv_postdatafrom_23_0= RULE_STRING
            {
            lv_postdatafrom_23_0=(Token)match(input,RULE_STRING,FOLLOW_41); 

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

            otherlv_24=(Token)match(input,62,FOLLOW_4); 

            			newLeafNode(otherlv_24, grammarAccess.getRestAccess().getUpdateBodyWithKeyword_24());
            		
            otherlv_25=(Token)match(input,12,FOLLOW_42); 

            			newLeafNode(otherlv_25, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_25());
            		
            otherlv_26=(Token)match(input,63,FOLLOW_11); 

            			newLeafNode(otherlv_26, grammarAccess.getRestAccess().getParentKeyword_26());
            		
            otherlv_27=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_27, grammarAccess.getRestAccess().getAsKeyword_27());
            		
            // InternalDsl.g:3623:3: ( (lv_parentName_28_0= RULE_STRING ) )
            // InternalDsl.g:3624:4: (lv_parentName_28_0= RULE_STRING )
            {
            // InternalDsl.g:3624:4: (lv_parentName_28_0= RULE_STRING )
            // InternalDsl.g:3625:5: lv_parentName_28_0= RULE_STRING
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

            // InternalDsl.g:3641:3: ( (lv_parentdata_29_0= ruleSelectStatement ) )
            // InternalDsl.g:3642:4: (lv_parentdata_29_0= ruleSelectStatement )
            {
            // InternalDsl.g:3642:4: (lv_parentdata_29_0= ruleSelectStatement )
            // InternalDsl.g:3643:5: lv_parentdata_29_0= ruleSelectStatement
            {

            					newCompositeNode(grammarAccess.getRestAccess().getParentdataSelectStatementParserRuleCall_29_0());
            				
            pushFollow(FOLLOW_43);
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

            // InternalDsl.g:3660:3: ( (lv_parts_30_0= ruleRestPart ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==65) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalDsl.g:3661:4: (lv_parts_30_0= ruleRestPart )
            	    {
            	    // InternalDsl.g:3661:4: (lv_parts_30_0= ruleRestPart )
            	    // InternalDsl.g:3662:5: lv_parts_30_0= ruleRestPart
            	    {

            	    					newCompositeNode(grammarAccess.getRestAccess().getPartsRestPartParserRuleCall_30_0());
            	    				
            	    pushFollow(FOLLOW_43);
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

            otherlv_31=(Token)match(input,13,FOLLOW_16); 

            			newLeafNode(otherlv_31, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_31());
            		
            otherlv_32=(Token)match(input,23,FOLLOW_3); 

            			newLeafNode(otherlv_32, grammarAccess.getRestAccess().getIntoKeyword_32());
            		
            // InternalDsl.g:3687:3: ( (lv_ackdatato_33_0= RULE_STRING ) )
            // InternalDsl.g:3688:4: (lv_ackdatato_33_0= RULE_STRING )
            {
            // InternalDsl.g:3688:4: (lv_ackdatato_33_0= RULE_STRING )
            // InternalDsl.g:3689:5: lv_ackdatato_33_0= RULE_STRING
            {
            lv_ackdatato_33_0=(Token)match(input,RULE_STRING,FOLLOW_44); 

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

            otherlv_34=(Token)match(input,64,FOLLOW_4); 

            			newLeafNode(otherlv_34, grammarAccess.getRestAccess().getStoreAckAtKeyword_34());
            		
            otherlv_35=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_35, grammarAccess.getRestAccess().getLeftCurlyBracketKeyword_35());
            		
            // InternalDsl.g:3713:3: ( (lv_ackdata_36_0= ruleSelectStatement ) )
            // InternalDsl.g:3714:4: (lv_ackdata_36_0= ruleSelectStatement )
            {
            // InternalDsl.g:3714:4: (lv_ackdata_36_0= ruleSelectStatement )
            // InternalDsl.g:3715:5: lv_ackdata_36_0= ruleSelectStatement
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
            		
            otherlv_38=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_38, grammarAccess.getRestAccess().getRightCurlyBracketKeyword_38());
            		
            otherlv_39=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_39, grammarAccess.getRestAccess().getOnConditionKeyword_39());
            		
            // InternalDsl.g:3744:3: ( (lv_condition_40_0= ruleExpression ) )
            // InternalDsl.g:3745:4: (lv_condition_40_0= ruleExpression )
            {
            // InternalDsl.g:3745:4: (lv_condition_40_0= ruleExpression )
            // InternalDsl.g:3746:5: lv_condition_40_0= ruleExpression
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
    // InternalDsl.g:3767:1: entryRuleRestPart returns [EObject current=null] : iv_ruleRestPart= ruleRestPart EOF ;
    public final EObject entryRuleRestPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRestPart = null;


        try {
            // InternalDsl.g:3767:49: (iv_ruleRestPart= ruleRestPart EOF )
            // InternalDsl.g:3768:2: iv_ruleRestPart= ruleRestPart EOF
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
    // InternalDsl.g:3774:1: ruleRestPart returns [EObject current=null] : (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) ) ;
    public final EObject ruleRestPart() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_partName_2_0=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_partData_4_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:3780:2: ( (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) ) )
            // InternalDsl.g:3781:2: (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) )
            {
            // InternalDsl.g:3781:2: (otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) ) )
            // InternalDsl.g:3782:3: otherlv_0= 'part' otherlv_1= 'as' ( (lv_partName_2_0= RULE_STRING ) ) otherlv_3= 'with' ( (lv_partData_4_0= ruleSelectStatement ) )
            {
            otherlv_0=(Token)match(input,65,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getRestPartAccess().getPartKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getRestPartAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3790:3: ( (lv_partName_2_0= RULE_STRING ) )
            // InternalDsl.g:3791:4: (lv_partName_2_0= RULE_STRING )
            {
            // InternalDsl.g:3791:4: (lv_partName_2_0= RULE_STRING )
            // InternalDsl.g:3792:5: lv_partName_2_0= RULE_STRING
            {
            lv_partName_2_0=(Token)match(input,RULE_STRING,FOLLOW_45); 

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

            otherlv_3=(Token)match(input,66,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getRestPartAccess().getWithKeyword_3());
            		
            // InternalDsl.g:3812:3: ( (lv_partData_4_0= ruleSelectStatement ) )
            // InternalDsl.g:3813:4: (lv_partData_4_0= ruleSelectStatement )
            {
            // InternalDsl.g:3813:4: (lv_partData_4_0= ruleSelectStatement )
            // InternalDsl.g:3814:5: lv_partData_4_0= ruleSelectStatement
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
    // InternalDsl.g:3835:1: entryRuleTrelloGET returns [EObject current=null] : iv_ruleTrelloGET= ruleTrelloGET EOF ;
    public final EObject entryRuleTrelloGET() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTrelloGET = null;


        try {
            // InternalDsl.g:3835:50: (iv_ruleTrelloGET= ruleTrelloGET EOF )
            // InternalDsl.g:3836:2: iv_ruleTrelloGET= ruleTrelloGET EOF
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
    // InternalDsl.g:3842:1: ruleTrelloGET returns [EObject current=null] : (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:3848:2: ( (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:3849:2: (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:3849:2: (otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:3850:3: otherlv_0= 'trelloget' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'from-board' ( (lv_board_10_0= RULE_STRING ) ) otherlv_11= 'to' ( (lv_target_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,67,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getTrelloGETAccess().getTrellogetKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTrelloGETAccess().getAsKeyword_1());
            		
            // InternalDsl.g:3858:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:3859:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:3859:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:3860:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getTrelloGETAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:3880:3: ( (lv_authtoken_4_0= RULE_STRING ) )
            // InternalDsl.g:3881:4: (lv_authtoken_4_0= RULE_STRING )
            {
            // InternalDsl.g:3881:4: (lv_authtoken_4_0= RULE_STRING )
            // InternalDsl.g:3882:5: lv_authtoken_4_0= RULE_STRING
            {
            lv_authtoken_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getTrelloGETAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:3902:3: ( (lv_key_6_0= RULE_STRING ) )
            // InternalDsl.g:3903:4: (lv_key_6_0= RULE_STRING )
            {
            // InternalDsl.g:3903:4: (lv_key_6_0= RULE_STRING )
            // InternalDsl.g:3904:5: lv_key_6_0= RULE_STRING
            {
            lv_key_6_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

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

            otherlv_7=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getTrelloGETAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:3924:3: ( (lv_useraccount_8_0= RULE_STRING ) )
            // InternalDsl.g:3925:4: (lv_useraccount_8_0= RULE_STRING )
            {
            // InternalDsl.g:3925:4: (lv_useraccount_8_0= RULE_STRING )
            // InternalDsl.g:3926:5: lv_useraccount_8_0= RULE_STRING
            {
            lv_useraccount_8_0=(Token)match(input,RULE_STRING,FOLLOW_46); 

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

            otherlv_9=(Token)match(input,68,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getTrelloGETAccess().getFromBoardKeyword_9());
            		
            // InternalDsl.g:3946:3: ( (lv_board_10_0= RULE_STRING ) )
            // InternalDsl.g:3947:4: (lv_board_10_0= RULE_STRING )
            {
            // InternalDsl.g:3947:4: (lv_board_10_0= RULE_STRING )
            // InternalDsl.g:3948:5: lv_board_10_0= RULE_STRING
            {
            lv_board_10_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

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

            otherlv_11=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getTrelloGETAccess().getToKeyword_11());
            		
            // InternalDsl.g:3968:3: ( (lv_target_12_0= RULE_STRING ) )
            // InternalDsl.g:3969:4: (lv_target_12_0= RULE_STRING )
            {
            // InternalDsl.g:3969:4: (lv_target_12_0= RULE_STRING )
            // InternalDsl.g:3970:5: lv_target_12_0= RULE_STRING
            {
            lv_target_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_13=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getTrelloGETAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getTrelloGETAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:3994:3: ( (lv_value_15_0= ruleSelectStatement ) )
            // InternalDsl.g:3995:4: (lv_value_15_0= ruleSelectStatement )
            {
            // InternalDsl.g:3995:4: (lv_value_15_0= ruleSelectStatement )
            // InternalDsl.g:3996:5: lv_value_15_0= ruleSelectStatement
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

            otherlv_16=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_16, grammarAccess.getTrelloGETAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_17, grammarAccess.getTrelloGETAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:4021:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:4022:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:4022:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:4023:5: lv_condition_18_0= ruleExpression
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
    // InternalDsl.g:4044:1: entryRuleTrelloPUT returns [EObject current=null] : iv_ruleTrelloPUT= ruleTrelloPUT EOF ;
    public final EObject entryRuleTrelloPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTrelloPUT = null;


        try {
            // InternalDsl.g:4044:50: (iv_ruleTrelloPUT= ruleTrelloPUT EOF )
            // InternalDsl.g:4045:2: iv_ruleTrelloPUT= ruleTrelloPUT EOF
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
    // InternalDsl.g:4051:1: ruleTrelloPUT returns [EObject current=null] : (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:4057:2: ( (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) ) )
            // InternalDsl.g:4058:2: (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4058:2: (otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) ) )
            // InternalDsl.g:4059:3: otherlv_0= 'trelloput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'secured-by' ( (lv_authtoken_4_0= RULE_STRING ) ) otherlv_5= 'with-key' ( (lv_key_6_0= RULE_STRING ) ) otherlv_7= 'through-user' ( (lv_useraccount_8_0= RULE_STRING ) ) otherlv_9= 'for-list' ( (lv_list_10_0= RULE_STRING ) ) otherlv_11= 'from-source' ( (lv_source_12_0= RULE_STRING ) ) otherlv_13= 'using' otherlv_14= '{' ( (lv_value_15_0= ruleSelectStatement ) ) otherlv_16= '}' otherlv_17= 'on-condition' ( (lv_condition_18_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,69,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getTrelloPUTAccess().getTrelloputKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTrelloPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4067:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4068:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4068:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4069:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_3=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getTrelloPUTAccess().getSecuredByKeyword_3());
            		
            // InternalDsl.g:4089:3: ( (lv_authtoken_4_0= RULE_STRING ) )
            // InternalDsl.g:4090:4: (lv_authtoken_4_0= RULE_STRING )
            {
            // InternalDsl.g:4090:4: (lv_authtoken_4_0= RULE_STRING )
            // InternalDsl.g:4091:5: lv_authtoken_4_0= RULE_STRING
            {
            lv_authtoken_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

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

            otherlv_5=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getTrelloPUTAccess().getWithKeyKeyword_5());
            		
            // InternalDsl.g:4111:3: ( (lv_key_6_0= RULE_STRING ) )
            // InternalDsl.g:4112:4: (lv_key_6_0= RULE_STRING )
            {
            // InternalDsl.g:4112:4: (lv_key_6_0= RULE_STRING )
            // InternalDsl.g:4113:5: lv_key_6_0= RULE_STRING
            {
            lv_key_6_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

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

            otherlv_7=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getTrelloPUTAccess().getThroughUserKeyword_7());
            		
            // InternalDsl.g:4133:3: ( (lv_useraccount_8_0= RULE_STRING ) )
            // InternalDsl.g:4134:4: (lv_useraccount_8_0= RULE_STRING )
            {
            // InternalDsl.g:4134:4: (lv_useraccount_8_0= RULE_STRING )
            // InternalDsl.g:4135:5: lv_useraccount_8_0= RULE_STRING
            {
            lv_useraccount_8_0=(Token)match(input,RULE_STRING,FOLLOW_47); 

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

            otherlv_9=(Token)match(input,70,FOLLOW_3); 

            			newLeafNode(otherlv_9, grammarAccess.getTrelloPUTAccess().getForListKeyword_9());
            		
            // InternalDsl.g:4155:3: ( (lv_list_10_0= RULE_STRING ) )
            // InternalDsl.g:4156:4: (lv_list_10_0= RULE_STRING )
            {
            // InternalDsl.g:4156:4: (lv_list_10_0= RULE_STRING )
            // InternalDsl.g:4157:5: lv_list_10_0= RULE_STRING
            {
            lv_list_10_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

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

            otherlv_11=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_11, grammarAccess.getTrelloPUTAccess().getFromSourceKeyword_11());
            		
            // InternalDsl.g:4177:3: ( (lv_source_12_0= RULE_STRING ) )
            // InternalDsl.g:4178:4: (lv_source_12_0= RULE_STRING )
            {
            // InternalDsl.g:4178:4: (lv_source_12_0= RULE_STRING )
            // InternalDsl.g:4179:5: lv_source_12_0= RULE_STRING
            {
            lv_source_12_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_13=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_13, grammarAccess.getTrelloPUTAccess().getUsingKeyword_13());
            		
            otherlv_14=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_14, grammarAccess.getTrelloPUTAccess().getLeftCurlyBracketKeyword_14());
            		
            // InternalDsl.g:4203:3: ( (lv_value_15_0= ruleSelectStatement ) )
            // InternalDsl.g:4204:4: (lv_value_15_0= ruleSelectStatement )
            {
            // InternalDsl.g:4204:4: (lv_value_15_0= ruleSelectStatement )
            // InternalDsl.g:4205:5: lv_value_15_0= ruleSelectStatement
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

            otherlv_16=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_16, grammarAccess.getTrelloPUTAccess().getRightCurlyBracketKeyword_16());
            		
            otherlv_17=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_17, grammarAccess.getTrelloPUTAccess().getOnConditionKeyword_17());
            		
            // InternalDsl.g:4230:3: ( (lv_condition_18_0= ruleExpression ) )
            // InternalDsl.g:4231:4: (lv_condition_18_0= ruleExpression )
            {
            // InternalDsl.g:4231:4: (lv_condition_18_0= ruleExpression )
            // InternalDsl.g:4232:5: lv_condition_18_0= ruleExpression
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
    // InternalDsl.g:4253:1: entryRuleFetch returns [EObject current=null] : iv_ruleFetch= ruleFetch EOF ;
    public final EObject entryRuleFetch() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFetch = null;


        try {
            // InternalDsl.g:4253:46: (iv_ruleFetch= ruleFetch EOF )
            // InternalDsl.g:4254:2: iv_ruleFetch= ruleFetch EOF
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
    // InternalDsl.g:4260:1: ruleFetch returns [EObject current=null] : (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:4266:2: ( (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) )
            // InternalDsl.g:4267:2: (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4267:2: (otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            // InternalDsl.g:4268:3: otherlv_0= 'assign' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'source' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= RULE_STRING ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,71,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getFetchAccess().getAssignKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFetchAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4276:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4277:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4277:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4278:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_48); 

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

            otherlv_3=(Token)match(input,72,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getFetchAccess().getSourceKeyword_3());
            		
            // InternalDsl.g:4298:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:4299:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:4299:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:4300:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_5=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_5, grammarAccess.getFetchAccess().getUsingKeyword_5());
            		
            otherlv_6=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_6, grammarAccess.getFetchAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalDsl.g:4324:3: ( (lv_value_7_0= RULE_STRING ) )
            // InternalDsl.g:4325:4: (lv_value_7_0= RULE_STRING )
            {
            // InternalDsl.g:4325:4: (lv_value_7_0= RULE_STRING )
            // InternalDsl.g:4326:5: lv_value_7_0= RULE_STRING
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

            otherlv_8=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_8, grammarAccess.getFetchAccess().getRightCurlyBracketKeyword_8());
            		
            otherlv_9=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_9, grammarAccess.getFetchAccess().getOnConditionKeyword_9());
            		
            // InternalDsl.g:4350:3: ( (lv_condition_10_0= ruleExpression ) )
            // InternalDsl.g:4351:4: (lv_condition_10_0= ruleExpression )
            {
            // InternalDsl.g:4351:4: (lv_condition_10_0= ruleExpression )
            // InternalDsl.g:4352:5: lv_condition_10_0= ruleExpression
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
    // InternalDsl.g:4373:1: entryRuleCallprocess returns [EObject current=null] : iv_ruleCallprocess= ruleCallprocess EOF ;
    public final EObject entryRuleCallprocess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCallprocess = null;


        try {
            // InternalDsl.g:4373:52: (iv_ruleCallprocess= ruleCallprocess EOF )
            // InternalDsl.g:4374:2: iv_ruleCallprocess= ruleCallprocess EOF
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
    // InternalDsl.g:4380:1: ruleCallprocess returns [EObject current=null] : (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:4386:2: ( (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:4387:2: (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4387:2: (otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:4388:3: otherlv_0= 'callprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,73,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getCallprocessAccess().getCallprocessKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getCallprocessAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4396:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4397:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4397:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4398:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_49); 

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

            otherlv_3=(Token)match(input,74,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getCallprocessAccess().getWithTargetKeyword_3());
            		
            // InternalDsl.g:4418:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:4419:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:4419:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:4420:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_50); 

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

            otherlv_5=(Token)match(input,75,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getCallprocessAccess().getFromFileKeyword_5());
            		
            // InternalDsl.g:4440:3: ( (lv_source_6_0= RULE_STRING ) )
            // InternalDsl.g:4441:4: (lv_source_6_0= RULE_STRING )
            {
            // InternalDsl.g:4441:4: (lv_source_6_0= RULE_STRING )
            // InternalDsl.g:4442:5: lv_source_6_0= RULE_STRING
            {
            lv_source_6_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_7=(Token)match(input,24,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getCallprocessAccess().getUsingKeyword_7());
            		
            // InternalDsl.g:4462:3: ( (lv_datasource_8_0= RULE_STRING ) )
            // InternalDsl.g:4463:4: (lv_datasource_8_0= RULE_STRING )
            {
            // InternalDsl.g:4463:4: (lv_datasource_8_0= RULE_STRING )
            // InternalDsl.g:4464:5: lv_datasource_8_0= RULE_STRING
            {
            lv_datasource_8_0=(Token)match(input,RULE_STRING,FOLLOW_51); 

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

            otherlv_9=(Token)match(input,76,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getCallprocessAccess().getForEveryKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getCallprocessAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:4488:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:4489:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:4489:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:4490:5: lv_value_11_0= ruleSelectStatement
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

            otherlv_12=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_12, grammarAccess.getCallprocessAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_13, grammarAccess.getCallprocessAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:4515:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:4516:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:4516:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:4517:5: lv_condition_14_0= ruleExpression
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
    // InternalDsl.g:4538:1: entryRuleForkprocess returns [EObject current=null] : iv_ruleForkprocess= ruleForkprocess EOF ;
    public final EObject entryRuleForkprocess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForkprocess = null;


        try {
            // InternalDsl.g:4538:52: (iv_ruleForkprocess= ruleForkprocess EOF )
            // InternalDsl.g:4539:2: iv_ruleForkprocess= ruleForkprocess EOF
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
    // InternalDsl.g:4545:1: ruleForkprocess returns [EObject current=null] : (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:4551:2: ( (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:4552:2: (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4552:2: (otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:4553:3: otherlv_0= 'forkprocess' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-target' ( (lv_target_4_0= RULE_STRING ) ) otherlv_5= 'from-file' ( (lv_source_6_0= RULE_STRING ) ) otherlv_7= 'using' ( (lv_datasource_8_0= RULE_STRING ) ) otherlv_9= 'for-every' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,77,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getForkprocessAccess().getForkprocessKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getForkprocessAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4561:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4562:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4562:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4563:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_49); 

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

            otherlv_3=(Token)match(input,74,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getForkprocessAccess().getWithTargetKeyword_3());
            		
            // InternalDsl.g:4583:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalDsl.g:4584:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalDsl.g:4584:4: (lv_target_4_0= RULE_STRING )
            // InternalDsl.g:4585:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_50); 

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

            otherlv_5=(Token)match(input,75,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getForkprocessAccess().getFromFileKeyword_5());
            		
            // InternalDsl.g:4605:3: ( (lv_source_6_0= RULE_STRING ) )
            // InternalDsl.g:4606:4: (lv_source_6_0= RULE_STRING )
            {
            // InternalDsl.g:4606:4: (lv_source_6_0= RULE_STRING )
            // InternalDsl.g:4607:5: lv_source_6_0= RULE_STRING
            {
            lv_source_6_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_7=(Token)match(input,24,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getForkprocessAccess().getUsingKeyword_7());
            		
            // InternalDsl.g:4627:3: ( (lv_datasource_8_0= RULE_STRING ) )
            // InternalDsl.g:4628:4: (lv_datasource_8_0= RULE_STRING )
            {
            // InternalDsl.g:4628:4: (lv_datasource_8_0= RULE_STRING )
            // InternalDsl.g:4629:5: lv_datasource_8_0= RULE_STRING
            {
            lv_datasource_8_0=(Token)match(input,RULE_STRING,FOLLOW_51); 

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

            otherlv_9=(Token)match(input,76,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getForkprocessAccess().getForEveryKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getForkprocessAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:4653:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:4654:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:4654:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:4655:5: lv_value_11_0= ruleSelectStatement
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

            otherlv_12=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_12, grammarAccess.getForkprocessAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_13, grammarAccess.getForkprocessAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:4680:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:4681:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:4681:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:4682:5: lv_condition_14_0= ruleExpression
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
    // InternalDsl.g:4703:1: entryRuleUpdatedaudit returns [EObject current=null] : iv_ruleUpdatedaudit= ruleUpdatedaudit EOF ;
    public final EObject entryRuleUpdatedaudit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUpdatedaudit = null;


        try {
            // InternalDsl.g:4703:53: (iv_ruleUpdatedaudit= ruleUpdatedaudit EOF )
            // InternalDsl.g:4704:2: iv_ruleUpdatedaudit= ruleUpdatedaudit EOF
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
    // InternalDsl.g:4710:1: ruleUpdatedaudit returns [EObject current=null] : (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:4716:2: ( (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:4717:2: (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4717:2: (otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:4718:3: otherlv_0= 'log' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'logto' ( (lv_logsink_4_0= RULE_STRING ) ) otherlv_5= 'in' ( (lv_datasource_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,78,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getUpdatedauditAccess().getLogKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getUpdatedauditAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4726:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4727:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4727:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4728:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_52); 

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

            otherlv_3=(Token)match(input,79,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getUpdatedauditAccess().getLogtoKeyword_3());
            		
            // InternalDsl.g:4748:3: ( (lv_logsink_4_0= RULE_STRING ) )
            // InternalDsl.g:4749:4: (lv_logsink_4_0= RULE_STRING )
            {
            // InternalDsl.g:4749:4: (lv_logsink_4_0= RULE_STRING )
            // InternalDsl.g:4750:5: lv_logsink_4_0= RULE_STRING
            {
            lv_logsink_4_0=(Token)match(input,RULE_STRING,FOLLOW_53); 

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

            otherlv_5=(Token)match(input,80,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getUpdatedauditAccess().getInKeyword_5());
            		
            // InternalDsl.g:4770:3: ( (lv_datasource_6_0= RULE_STRING ) )
            // InternalDsl.g:4771:4: (lv_datasource_6_0= RULE_STRING )
            {
            // InternalDsl.g:4771:4: (lv_datasource_6_0= RULE_STRING )
            // InternalDsl.g:4772:5: lv_datasource_6_0= RULE_STRING
            {
            lv_datasource_6_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_7=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getUpdatedauditAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getUpdatedauditAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:4796:3: ( (lv_value_9_0= ruleSelectStatement ) )
            // InternalDsl.g:4797:4: (lv_value_9_0= ruleSelectStatement )
            {
            // InternalDsl.g:4797:4: (lv_value_9_0= ruleSelectStatement )
            // InternalDsl.g:4798:5: lv_value_9_0= ruleSelectStatement
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

            otherlv_10=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_10, grammarAccess.getUpdatedauditAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_11, grammarAccess.getUpdatedauditAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:4823:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:4824:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:4824:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:4825:5: lv_condition_12_0= ruleExpression
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
    // InternalDsl.g:4846:1: entryRuleClickSendSms returns [EObject current=null] : iv_ruleClickSendSms= ruleClickSendSms EOF ;
    public final EObject entryRuleClickSendSms() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClickSendSms = null;


        try {
            // InternalDsl.g:4846:53: (iv_ruleClickSendSms= ruleClickSendSms EOF )
            // InternalDsl.g:4847:2: iv_ruleClickSendSms= ruleClickSendSms EOF
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
    // InternalDsl.g:4853:1: ruleClickSendSms returns [EObject current=null] : (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:4859:2: ( (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:4860:2: (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:4860:2: (otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:4861:3: otherlv_0= 'c2sms' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'with-user' ( (lv_userid_4_0= RULE_STRING ) ) otherlv_5= 'secured-by' ( (lv_securityKey_6_0= RULE_STRING ) ) otherlv_7= 'from-source' ( (lv_target_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= RULE_STRING ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,81,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getClickSendSmsAccess().getC2smsKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getClickSendSmsAccess().getAsKeyword_1());
            		
            // InternalDsl.g:4869:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:4870:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:4870:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:4871:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_54); 

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

            otherlv_3=(Token)match(input,82,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getClickSendSmsAccess().getWithUserKeyword_3());
            		
            // InternalDsl.g:4891:3: ( (lv_userid_4_0= RULE_STRING ) )
            // InternalDsl.g:4892:4: (lv_userid_4_0= RULE_STRING )
            {
            // InternalDsl.g:4892:4: (lv_userid_4_0= RULE_STRING )
            // InternalDsl.g:4893:5: lv_userid_4_0= RULE_STRING
            {
            lv_userid_4_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

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

            otherlv_5=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getClickSendSmsAccess().getSecuredByKeyword_5());
            		
            // InternalDsl.g:4913:3: ( (lv_securityKey_6_0= RULE_STRING ) )
            // InternalDsl.g:4914:4: (lv_securityKey_6_0= RULE_STRING )
            {
            // InternalDsl.g:4914:4: (lv_securityKey_6_0= RULE_STRING )
            // InternalDsl.g:4915:5: lv_securityKey_6_0= RULE_STRING
            {
            lv_securityKey_6_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

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

            otherlv_7=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getClickSendSmsAccess().getFromSourceKeyword_7());
            		
            // InternalDsl.g:4935:3: ( (lv_target_8_0= RULE_STRING ) )
            // InternalDsl.g:4936:4: (lv_target_8_0= RULE_STRING )
            {
            // InternalDsl.g:4936:4: (lv_target_8_0= RULE_STRING )
            // InternalDsl.g:4937:5: lv_target_8_0= RULE_STRING
            {
            lv_target_8_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_9=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getClickSendSmsAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getClickSendSmsAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:4961:3: ( (lv_value_11_0= RULE_STRING ) )
            // InternalDsl.g:4962:4: (lv_value_11_0= RULE_STRING )
            {
            // InternalDsl.g:4962:4: (lv_value_11_0= RULE_STRING )
            // InternalDsl.g:4963:5: lv_value_11_0= RULE_STRING
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

            otherlv_12=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_12, grammarAccess.getClickSendSmsAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_13, grammarAccess.getClickSendSmsAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:4987:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:4988:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:4988:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:4989:5: lv_condition_14_0= ruleExpression
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
    // InternalDsl.g:5010:1: entryRuleSlackPUT returns [EObject current=null] : iv_ruleSlackPUT= ruleSlackPUT EOF ;
    public final EObject entryRuleSlackPUT() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSlackPUT = null;


        try {
            // InternalDsl.g:5010:49: (iv_ruleSlackPUT= ruleSlackPUT EOF )
            // InternalDsl.g:5011:2: iv_ruleSlackPUT= ruleSlackPUT EOF
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
    // InternalDsl.g:5017:1: ruleSlackPUT returns [EObject current=null] : (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:5023:2: ( (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:5024:2: (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5024:2: (otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:5025:3: otherlv_0= 'slackput' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (lv_team_4_0= RULE_STRING ) ) otherlv_5= 'on' ( (lv_channel_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= RULE_STRING ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,83,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getSlackPUTAccess().getSlackputKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getSlackPUTAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5033:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5034:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5034:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5035:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

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

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getSlackPUTAccess().getToKeyword_3());
            		
            // InternalDsl.g:5055:3: ( (lv_team_4_0= RULE_STRING ) )
            // InternalDsl.g:5056:4: (lv_team_4_0= RULE_STRING )
            {
            // InternalDsl.g:5056:4: (lv_team_4_0= RULE_STRING )
            // InternalDsl.g:5057:5: lv_team_4_0= RULE_STRING
            {
            lv_team_4_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

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

            otherlv_5=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getSlackPUTAccess().getOnKeyword_5());
            		
            // InternalDsl.g:5077:3: ( (lv_channel_6_0= RULE_STRING ) )
            // InternalDsl.g:5078:4: (lv_channel_6_0= RULE_STRING )
            {
            // InternalDsl.g:5078:4: (lv_channel_6_0= RULE_STRING )
            // InternalDsl.g:5079:5: lv_channel_6_0= RULE_STRING
            {
            lv_channel_6_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_7=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getSlackPUTAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getSlackPUTAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:5103:3: ( (lv_value_9_0= RULE_STRING ) )
            // InternalDsl.g:5104:4: (lv_value_9_0= RULE_STRING )
            {
            // InternalDsl.g:5104:4: (lv_value_9_0= RULE_STRING )
            // InternalDsl.g:5105:5: lv_value_9_0= RULE_STRING
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

            otherlv_10=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_10, grammarAccess.getSlackPUTAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_11, grammarAccess.getSlackPUTAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:5129:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:5130:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:5130:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:5131:5: lv_condition_12_0= ruleExpression
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
    // InternalDsl.g:5152:1: entryRuleCopydata returns [EObject current=null] : iv_ruleCopydata= ruleCopydata EOF ;
    public final EObject entryRuleCopydata() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCopydata = null;


        try {
            // InternalDsl.g:5152:49: (iv_ruleCopydata= ruleCopydata EOF )
            // InternalDsl.g:5153:2: iv_ruleCopydata= ruleCopydata EOF
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
    // InternalDsl.g:5159:1: ruleCopydata returns [EObject current=null] : (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:5165:2: ( (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) ) )
            // InternalDsl.g:5166:2: (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5166:2: (otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) ) )
            // InternalDsl.g:5167:3: otherlv_0= 'copydata' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'using' otherlv_8= '{' ( (lv_value_9_0= ruleSelectStatement ) ) otherlv_10= '}' otherlv_11= 'on-condition' ( (lv_condition_12_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,84,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getCopydataAccess().getCopydataKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getCopydataAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5175:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5176:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5176:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5177:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

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

            otherlv_3=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getCopydataAccess().getFromKeyword_3());
            		
            // InternalDsl.g:5197:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:5198:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:5198:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:5199:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

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

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getCopydataAccess().getToKeyword_5());
            		
            // InternalDsl.g:5219:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:5220:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:5220:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:5221:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_7=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_7, grammarAccess.getCopydataAccess().getUsingKeyword_7());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getCopydataAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalDsl.g:5245:3: ( (lv_value_9_0= ruleSelectStatement ) )
            // InternalDsl.g:5246:4: (lv_value_9_0= ruleSelectStatement )
            {
            // InternalDsl.g:5246:4: (lv_value_9_0= ruleSelectStatement )
            // InternalDsl.g:5247:5: lv_value_9_0= ruleSelectStatement
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

            otherlv_10=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_10, grammarAccess.getCopydataAccess().getRightCurlyBracketKeyword_10());
            		
            otherlv_11=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_11, grammarAccess.getCopydataAccess().getOnConditionKeyword_11());
            		
            // InternalDsl.g:5272:3: ( (lv_condition_12_0= ruleExpression ) )
            // InternalDsl.g:5273:4: (lv_condition_12_0= ruleExpression )
            {
            // InternalDsl.g:5273:4: (lv_condition_12_0= ruleExpression )
            // InternalDsl.g:5274:5: lv_condition_12_0= ruleExpression
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
    // InternalDsl.g:5295:1: entryRuleWriteCsv returns [EObject current=null] : iv_ruleWriteCsv= ruleWriteCsv EOF ;
    public final EObject entryRuleWriteCsv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWriteCsv = null;


        try {
            // InternalDsl.g:5295:49: (iv_ruleWriteCsv= ruleWriteCsv EOF )
            // InternalDsl.g:5296:2: iv_ruleWriteCsv= ruleWriteCsv EOF
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
    // InternalDsl.g:5302:1: ruleWriteCsv returns [EObject current=null] : (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:5308:2: ( (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:5309:2: (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5309:2: (otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:5310:3: otherlv_0= 'writecsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,85,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getWriteCsvAccess().getWritecsvKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getWriteCsvAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5318:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5319:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5319:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5320:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

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

            otherlv_3=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getWriteCsvAccess().getFromKeyword_3());
            		
            // InternalDsl.g:5340:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:5341:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:5341:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:5342:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

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

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getWriteCsvAccess().getToKeyword_5());
            		
            // InternalDsl.g:5362:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:5363:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:5363:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:5364:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_45); 

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

            otherlv_7=(Token)match(input,66,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getWriteCsvAccess().getWithKeyword_7());
            		
            // InternalDsl.g:5384:3: ( (lv_delim_8_0= RULE_STRING ) )
            // InternalDsl.g:5385:4: (lv_delim_8_0= RULE_STRING )
            {
            // InternalDsl.g:5385:4: (lv_delim_8_0= RULE_STRING )
            // InternalDsl.g:5386:5: lv_delim_8_0= RULE_STRING
            {
            lv_delim_8_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_9=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getWriteCsvAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getWriteCsvAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:5410:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:5411:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:5411:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:5412:5: lv_value_11_0= ruleSelectStatement
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

            otherlv_12=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_12, grammarAccess.getWriteCsvAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_13, grammarAccess.getWriteCsvAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:5437:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:5438:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:5438:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:5439:5: lv_condition_14_0= ruleExpression
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
    // InternalDsl.g:5460:1: entryRuleLoadCsv returns [EObject current=null] : iv_ruleLoadCsv= ruleLoadCsv EOF ;
    public final EObject entryRuleLoadCsv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadCsv = null;


        try {
            // InternalDsl.g:5460:48: (iv_ruleLoadCsv= ruleLoadCsv EOF )
            // InternalDsl.g:5461:2: iv_ruleLoadCsv= ruleLoadCsv EOF
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
    // InternalDsl.g:5467:1: ruleLoadCsv returns [EObject current=null] : (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:5473:2: ( (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) ) )
            // InternalDsl.g:5474:2: (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5474:2: (otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) ) )
            // InternalDsl.g:5475:3: otherlv_0= 'loadcsv' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'from' ( (lv_source_4_0= RULE_STRING ) ) otherlv_5= 'to' ( (lv_to_6_0= RULE_STRING ) ) otherlv_7= 'with' ( (lv_delim_8_0= RULE_STRING ) ) otherlv_9= 'using' otherlv_10= '{' ( (lv_value_11_0= ruleSelectStatement ) ) otherlv_12= '}' otherlv_13= 'on-condition' ( (lv_condition_14_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,86,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getLoadCsvAccess().getLoadcsvKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getLoadCsvAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5483:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5484:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5484:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5485:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

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

            otherlv_3=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getLoadCsvAccess().getFromKeyword_3());
            		
            // InternalDsl.g:5505:3: ( (lv_source_4_0= RULE_STRING ) )
            // InternalDsl.g:5506:4: (lv_source_4_0= RULE_STRING )
            {
            // InternalDsl.g:5506:4: (lv_source_4_0= RULE_STRING )
            // InternalDsl.g:5507:5: lv_source_4_0= RULE_STRING
            {
            lv_source_4_0=(Token)match(input,RULE_STRING,FOLLOW_21); 

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

            otherlv_5=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getLoadCsvAccess().getToKeyword_5());
            		
            // InternalDsl.g:5527:3: ( (lv_to_6_0= RULE_STRING ) )
            // InternalDsl.g:5528:4: (lv_to_6_0= RULE_STRING )
            {
            // InternalDsl.g:5528:4: (lv_to_6_0= RULE_STRING )
            // InternalDsl.g:5529:5: lv_to_6_0= RULE_STRING
            {
            lv_to_6_0=(Token)match(input,RULE_STRING,FOLLOW_45); 

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

            otherlv_7=(Token)match(input,66,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getLoadCsvAccess().getWithKeyword_7());
            		
            // InternalDsl.g:5549:3: ( (lv_delim_8_0= RULE_STRING ) )
            // InternalDsl.g:5550:4: (lv_delim_8_0= RULE_STRING )
            {
            // InternalDsl.g:5550:4: (lv_delim_8_0= RULE_STRING )
            // InternalDsl.g:5551:5: lv_delim_8_0= RULE_STRING
            {
            lv_delim_8_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_9=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_9, grammarAccess.getLoadCsvAccess().getUsingKeyword_9());
            		
            otherlv_10=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getLoadCsvAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalDsl.g:5575:3: ( (lv_value_11_0= ruleSelectStatement ) )
            // InternalDsl.g:5576:4: (lv_value_11_0= ruleSelectStatement )
            {
            // InternalDsl.g:5576:4: (lv_value_11_0= ruleSelectStatement )
            // InternalDsl.g:5577:5: lv_value_11_0= ruleSelectStatement
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

            otherlv_12=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_12, grammarAccess.getLoadCsvAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_13=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_13, grammarAccess.getLoadCsvAccess().getOnConditionKeyword_13());
            		
            // InternalDsl.g:5602:3: ( (lv_condition_14_0= ruleExpression ) )
            // InternalDsl.g:5603:4: (lv_condition_14_0= ruleExpression )
            {
            // InternalDsl.g:5603:4: (lv_condition_14_0= ruleExpression )
            // InternalDsl.g:5604:5: lv_condition_14_0= ruleExpression
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
    // InternalDsl.g:5625:1: entryRuleTransform returns [EObject current=null] : iv_ruleTransform= ruleTransform EOF ;
    public final EObject entryRuleTransform() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTransform = null;


        try {
            // InternalDsl.g:5625:50: (iv_ruleTransform= ruleTransform EOF )
            // InternalDsl.g:5626:2: iv_ruleTransform= ruleTransform EOF
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
    // InternalDsl.g:5632:1: ruleTransform returns [EObject current=null] : (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) ;
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
            // InternalDsl.g:5638:2: ( (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) ) )
            // InternalDsl.g:5639:2: (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            {
            // InternalDsl.g:5639:2: (otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) ) )
            // InternalDsl.g:5640:3: otherlv_0= 'transform' otherlv_1= 'as' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'on' ( (lv_on_4_0= RULE_STRING ) ) otherlv_5= 'using' otherlv_6= '{' ( (lv_value_7_0= ruleNonSelectStatement ) ) otherlv_8= '}' otherlv_9= 'on-condition' ( (lv_condition_10_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,87,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getTransformAccess().getTransformKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTransformAccess().getAsKeyword_1());
            		
            // InternalDsl.g:5648:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalDsl.g:5649:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalDsl.g:5649:4: (lv_name_2_0= RULE_STRING )
            // InternalDsl.g:5650:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

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

            otherlv_3=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getTransformAccess().getOnKeyword_3());
            		
            // InternalDsl.g:5670:3: ( (lv_on_4_0= RULE_STRING ) )
            // InternalDsl.g:5671:4: (lv_on_4_0= RULE_STRING )
            {
            // InternalDsl.g:5671:4: (lv_on_4_0= RULE_STRING )
            // InternalDsl.g:5672:5: lv_on_4_0= RULE_STRING
            {
            lv_on_4_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

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

            otherlv_5=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_5, grammarAccess.getTransformAccess().getUsingKeyword_5());
            		
            otherlv_6=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_6, grammarAccess.getTransformAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalDsl.g:5696:3: ( (lv_value_7_0= ruleNonSelectStatement ) )
            // InternalDsl.g:5697:4: (lv_value_7_0= ruleNonSelectStatement )
            {
            // InternalDsl.g:5697:4: (lv_value_7_0= ruleNonSelectStatement )
            // InternalDsl.g:5698:5: lv_value_7_0= ruleNonSelectStatement
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

            otherlv_8=(Token)match(input,13,FOLLOW_18); 

            			newLeafNode(otherlv_8, grammarAccess.getTransformAccess().getRightCurlyBracketKeyword_8());
            		
            otherlv_9=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_9, grammarAccess.getTransformAccess().getOnConditionKeyword_9());
            		
            // InternalDsl.g:5723:3: ( (lv_condition_10_0= ruleExpression ) )
            // InternalDsl.g:5724:4: (lv_condition_10_0= ruleExpression )
            {
            // InternalDsl.g:5724:4: (lv_condition_10_0= ruleExpression )
            // InternalDsl.g:5725:5: lv_condition_10_0= ruleExpression
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
    // InternalDsl.g:5746:1: entryRuleSelectStatement returns [String current=null] : iv_ruleSelectStatement= ruleSelectStatement EOF ;
    public final String entryRuleSelectStatement() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSelectStatement = null;


        try {
            // InternalDsl.g:5746:55: (iv_ruleSelectStatement= ruleSelectStatement EOF )
            // InternalDsl.g:5747:2: iv_ruleSelectStatement= ruleSelectStatement EOF
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
    // InternalDsl.g:5753:1: ruleSelectStatement returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleSelectStatement() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalDsl.g:5759:2: (this_STRING_0= RULE_STRING )
            // InternalDsl.g:5760:2: this_STRING_0= RULE_STRING
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
    // InternalDsl.g:5770:1: entryRuleNonSelectStatement returns [String current=null] : iv_ruleNonSelectStatement= ruleNonSelectStatement EOF ;
    public final String entryRuleNonSelectStatement() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNonSelectStatement = null;


        try {
            // InternalDsl.g:5770:58: (iv_ruleNonSelectStatement= ruleNonSelectStatement EOF )
            // InternalDsl.g:5771:2: iv_ruleNonSelectStatement= ruleNonSelectStatement EOF
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
    // InternalDsl.g:5777:1: ruleNonSelectStatement returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleNonSelectStatement() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalDsl.g:5783:2: (this_STRING_0= RULE_STRING )
            // InternalDsl.g:5784:2: this_STRING_0= RULE_STRING
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
    // InternalDsl.g:5794:1: entryRuleCreateStatement returns [String current=null] : iv_ruleCreateStatement= ruleCreateStatement EOF ;
    public final String entryRuleCreateStatement() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCreateStatement = null;


        try {
            // InternalDsl.g:5794:55: (iv_ruleCreateStatement= ruleCreateStatement EOF )
            // InternalDsl.g:5795:2: iv_ruleCreateStatement= ruleCreateStatement EOF
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
    // InternalDsl.g:5801:1: ruleCreateStatement returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleCreateStatement() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalDsl.g:5807:2: (this_STRING_0= RULE_STRING )
            // InternalDsl.g:5808:2: this_STRING_0= RULE_STRING
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
    // InternalDsl.g:5818:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalDsl.g:5818:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalDsl.g:5819:2: iv_ruleExpression= ruleExpression EOF
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
    // InternalDsl.g:5825:1: ruleExpression returns [EObject current=null] : (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_lhs_1_0=null;
        Token lv_rhs_3_0=null;
        AntlrDatatypeRuleToken lv_operator_2_0 = null;



        	enterRule();

        try {
            // InternalDsl.g:5831:2: ( (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) ) )
            // InternalDsl.g:5832:2: (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) )
            {
            // InternalDsl.g:5832:2: (otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) ) )
            // InternalDsl.g:5833:3: otherlv_0= 'if' ( (lv_lhs_1_0= RULE_STRING ) ) ( (lv_operator_2_0= ruleOperator ) ) ( (lv_rhs_3_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,88,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getExpressionAccess().getIfKeyword_0());
            		
            // InternalDsl.g:5837:3: ( (lv_lhs_1_0= RULE_STRING ) )
            // InternalDsl.g:5838:4: (lv_lhs_1_0= RULE_STRING )
            {
            // InternalDsl.g:5838:4: (lv_lhs_1_0= RULE_STRING )
            // InternalDsl.g:5839:5: lv_lhs_1_0= RULE_STRING
            {
            lv_lhs_1_0=(Token)match(input,RULE_STRING,FOLLOW_55); 

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

            // InternalDsl.g:5855:3: ( (lv_operator_2_0= ruleOperator ) )
            // InternalDsl.g:5856:4: (lv_operator_2_0= ruleOperator )
            {
            // InternalDsl.g:5856:4: (lv_operator_2_0= ruleOperator )
            // InternalDsl.g:5857:5: lv_operator_2_0= ruleOperator
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

            // InternalDsl.g:5874:3: ( (lv_rhs_3_0= RULE_STRING ) )
            // InternalDsl.g:5875:4: (lv_rhs_3_0= RULE_STRING )
            {
            // InternalDsl.g:5875:4: (lv_rhs_3_0= RULE_STRING )
            // InternalDsl.g:5876:5: lv_rhs_3_0= RULE_STRING
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
    // InternalDsl.g:5896:1: entryRuleOperator returns [String current=null] : iv_ruleOperator= ruleOperator EOF ;
    public final String entryRuleOperator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOperator = null;


        try {
            // InternalDsl.g:5896:48: (iv_ruleOperator= ruleOperator EOF )
            // InternalDsl.g:5897:2: iv_ruleOperator= ruleOperator EOF
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
    // InternalDsl.g:5903:1: ruleOperator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' ) ;
    public final AntlrDatatypeRuleToken ruleOperator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalDsl.g:5909:2: ( (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' ) )
            // InternalDsl.g:5910:2: (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' )
            {
            // InternalDsl.g:5910:2: (kw= '<' | kw= '>' | kw= '==' | kw= 'contains' )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt6=1;
                }
                break;
            case 90:
                {
                alt6=2;
                }
                break;
            case 91:
                {
                alt6=3;
                }
                break;
            case 92:
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
                    // InternalDsl.g:5911:3: kw= '<'
                    {
                    kw=(Token)match(input,89,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getLessThanSignKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalDsl.g:5917:3: kw= '>'
                    {
                    kw=(Token)match(input,90,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getGreaterThanSignKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalDsl.g:5923:3: kw= '=='
                    {
                    kw=(Token)match(input,91,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getEqualsSignEqualsSignKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalDsl.g:5929:3: kw= 'contains'
                    {
                    kw=(Token)match(input,92,FOLLOW_2); 

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
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x02AF8C60A4022000L,0x0000000000FA62A8L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000002000L,0x0000000000000002L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000000L,0x000000001E000000L});

}