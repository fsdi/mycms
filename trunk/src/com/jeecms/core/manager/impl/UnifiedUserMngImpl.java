/*     */ package com.jeecms.core.manager.impl;
/*     */ 
/*     */ import com.jeecms.common.email.EmailSendTool;
/*     */ import com.jeecms.common.email.EmailSender;
/*     */ import com.jeecms.common.email.MessageTemplate;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.security.BadCredentialsException;
/*     */ import com.jeecms.common.security.UsernameNotFoundException;
/*     */ import com.jeecms.common.security.encoder.PwdEncoder;
/*     */ import com.jeecms.core.dao.UnifiedUserDao;
/*     */ import com.jeecms.core.entity.Config.ConfigLogin;
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import com.jeecms.core.manager.ConfigMng;
/*     */ import com.jeecms.core.manager.UnifiedUserMng;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ import org.apache.commons.lang.RandomStringUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.mail.javamail.JavaMailSenderImpl;
/*     */ import org.springframework.mail.javamail.MimeMessageHelper;
/*     */ import org.springframework.mail.javamail.MimeMessagePreparator;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class UnifiedUserMngImpl
/*     */   implements UnifiedUserMng
/*     */ {
/*     */   private ConfigMng configMng;
/*     */   private PwdEncoder pwdEncoder;
/*     */   private UnifiedUserDao dao;
/*     */ 
/*     */   public UnifiedUser passwordForgotten(Integer userId, EmailSender email, MessageTemplate tpl)
/*     */   {
/*  40 */     UnifiedUser user = findById(userId);
/*  41 */     String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
/*  42 */     user.setResetKey(uuid);
/*  43 */     String resetPwd = RandomStringUtils.randomNumeric(10);
/*  44 */     user.setResetPwd(resetPwd);
/*  45 */     senderEmail(user.getId(), user.getUsername(), user.getEmail(), 
/*  46 */       user.getResetKey(), user.getResetPwd(), email, tpl);
/*  47 */     return user;
/*     */   }
/*     */ 
/*     */   private void senderEmail(Integer uid, String username, String to, String resetKey, String resetPwd, EmailSender email, MessageTemplate tpl)
/*     */   {
///*  53 */     JavaMailSenderImpl sender = new JavaMailSenderImpl();
///*  54 */     sender.setHost(email.getHost());
///*  55 */     sender.setUsername(email.getUsername());
///*  56 */     sender.setPassword(email.getPassword());
///*  57 */     sender.send(new MimeMessagePreparator(email, tpl, to, uid, username, resetKey, resetPwd)
///*     */     {
///*     */       public void prepare(MimeMessage mimeMessage) throws MessagingException, UnsupportedEncodingException {
///*  60 */         MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, 
///*  61 */           false, this.val$email.getEncoding());
///*  62 */         msg.setSubject(this.val$tpl.getForgotPasswordSubject());
///*  63 */         msg.setTo(this.val$to);
///*  64 */         msg.setFrom(this.val$email.getUsername(), this.val$email.getPersonal());
///*  65 */         String text = this.val$tpl.getForgotPasswordText();
///*  66 */         text = StringUtils.replace(text, "${uid}", String.valueOf(this.val$uid));
///*  67 */         text = StringUtils.replace(text, "${username}", this.val$username);
///*  68 */         text = StringUtils.replace(text, "${resetKey}", this.val$resetKey);
///*  69 */         text = StringUtils.replace(text, "${resetPwd}", this.val$resetPwd);
///*  70 */         msg.setText(text);
///*     */       }
///*     */     });
/*     */   }
/*     */ 
/*     */   private void senderEmail(String username, String to, String activationCode, EmailSender email, MessageTemplate tpl)
/*     */     throws UnsupportedEncodingException, MessagingException
/*     */   {
/*  93 */     String text = tpl.getRegisterText();
/*  94 */     text = StringUtils.replace(text, "${username}", username);
/*  95 */     text = StringUtils.replace(text, "${activationCode}", activationCode);
/*  96 */     EmailSendTool sendEmail = new EmailSendTool(email.getHost(), 
/*  97 */       email.getUsername(), email.getPassword(), to, 
/*  98 */       tpl.getRegisterSubject(), text, email.getPersonal(), "", "");
/*  99 */     sendEmail.send();
/*     */   }
/*     */ 
/*     */   public UnifiedUser resetPassword(Integer userId) {
/* 103 */     UnifiedUser user = findById(userId);
/* 104 */     user.setPassword(this.pwdEncoder.encodePassword(user.getResetPwd()));
/* 105 */     user.setResetKey(null);
/* 106 */     user.setResetPwd(null);
/* 107 */     return user;
/*     */   }
/*     */ 
/*     */   public Integer errorRemaining(String username) {
/* 111 */     if (StringUtils.isBlank(username)) {
/* 112 */       return null;
/*     */     }
/* 114 */     UnifiedUser user = getByUsername(username);
/* 115 */     if (user == null) {
/* 116 */       return null;
/*     */     }
/* 118 */     long now = System.currentTimeMillis();
/* 119 */     ConfigLogin configLogin = this.configMng.getConfigLogin();
/* 120 */     int maxErrorTimes = configLogin.getErrorTimes().intValue();
/* 121 */     int maxErrorInterval = configLogin.getErrorInterval().intValue() * 60 * 1000;
/* 122 */     Integer errorCount = user.getErrorCount();
/* 123 */     Date errorTime = user.getErrorTime();
/* 124 */     if ((errorCount.intValue() <= 0) || (errorTime == null) || 
/* 125 */       (errorTime.getTime() + maxErrorInterval < now)) {
/* 126 */       return Integer.valueOf(maxErrorTimes);
/*     */     }
/* 128 */     return Integer.valueOf(maxErrorTimes - errorCount.intValue());
/*     */   }
/*     */ 
/*     */   public UnifiedUser login(String username, String password, String ip) throws UsernameNotFoundException, BadCredentialsException
/*     */   {
/* 133 */     UnifiedUser user = getByUsername(username);
/* 134 */     if (user == null) {
/* 135 */       throw new UsernameNotFoundException("username not found: " + 
/* 136 */         username);
/*     */     }
/* 138 */     if (!this.pwdEncoder.isPasswordValid(user.getPassword(), password)) {
/* 139 */       updateLoginError(user.getId(), ip);
/* 140 */       throw new BadCredentialsException("password invalid");
/*     */     }
/* 142 */     if (!user.getActivation().booleanValue()) {
/* 143 */       throw new BadCredentialsException("account not activated");
/*     */     }
/* 145 */     updateLoginSuccess(user.getId(), ip);
/* 146 */     return user;
/*     */   }
/*     */ 
/*     */   public void updateLoginSuccess(Integer userId, String ip) {
/* 150 */     UnifiedUser user = findById(userId);
/* 151 */     Date now = new Timestamp(System.currentTimeMillis());
/*     */ 
/* 153 */     user.setLoginCount(Integer.valueOf(user.getLoginCount().intValue() + 1));
/* 154 */     user.setLastLoginIp(ip);
/* 155 */     user.setLastLoginTime(now);
/*     */ 
/* 157 */     user.setErrorCount(Integer.valueOf(0));
/* 158 */     user.setErrorTime(null);
/* 159 */     user.setErrorIp(null);
/*     */   }
/*     */ 
/*     */   public void updateLoginError(Integer userId, String ip) {
/* 163 */     UnifiedUser user = findById(userId);
/* 164 */     Date now = new Timestamp(System.currentTimeMillis());
/* 165 */     ConfigLogin configLogin = this.configMng.getConfigLogin();
/* 166 */     int errorInterval = configLogin.getErrorInterval().intValue();
/* 167 */     Date errorTime = user.getErrorTime();
/*     */ 
/* 169 */     user.setErrorIp(ip);
/* 170 */     if ((errorTime == null) || 
/* 172 */       (errorTime.getTime() + errorInterval * 60 * 1000 < 
/* 172 */       now.getTime())) {
/* 173 */       user.setErrorTime(now);
/* 174 */       user.setErrorCount(Integer.valueOf(1));
/*     */     } else {
/* 176 */       user.setErrorCount(Integer.valueOf(user.getErrorCount().intValue() + 1));
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean usernameExist(String username) {
/* 181 */     return getByUsername(username) != null;
/*     */   }
/*     */ 
/*     */   public boolean emailExist(String email) {
/* 185 */     return this.dao.countByEmail(email) > 0;
/*     */   }
/*     */ 
/*     */   public UnifiedUser getByUsername(String username) {
/* 189 */     return this.dao.getByUsername(username);
/*     */   }
/*     */ 
/*     */   public List<UnifiedUser> getByEmail(String email) {
/* 193 */     return this.dao.getByEmail(email);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(int pageNo, int pageSize) {
/* 198 */     Pagination page = this.dao.getPage(pageNo, pageSize);
/* 199 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public UnifiedUser findById(Integer id) {
/* 204 */     UnifiedUser entity = this.dao.findById(id);
/* 205 */     return entity;
/*     */   }
/*     */ 
/*     */   public UnifiedUser save(String username, String email, String password, String ip)
/*     */   {
/* 210 */     Date now = new Timestamp(System.currentTimeMillis());
/* 211 */     UnifiedUser user = new UnifiedUser();
/* 212 */     user.setUsername(username);
/* 213 */     user.setEmail(email);
/* 214 */     user.setPassword(this.pwdEncoder.encodePassword(password));
/* 215 */     user.setRegisterIp(ip);
/* 216 */     user.setRegisterTime(now);
/* 217 */     user.setLastLoginIp(ip);
/* 218 */     user.setLastLoginTime(now);
/*     */ 
/* 220 */     user.setActivation(Boolean.valueOf(true));
/* 221 */     user.init();
/* 222 */     this.dao.save(user);
/* 223 */     return user;
/*     */   }
/*     */ 
/*     */   public UnifiedUser save(String username, String email, String password, String ip, Boolean activation, EmailSender sender, MessageTemplate msgTpl)
/*     */     throws UnsupportedEncodingException, MessagingException
/*     */   {
/* 229 */     Date now = new Timestamp(System.currentTimeMillis());
/* 230 */     UnifiedUser user = new UnifiedUser();
/* 231 */     user.setUsername(username);
/* 232 */     user.setEmail(email);
/* 233 */     user.setPassword(this.pwdEncoder.encodePassword(password));
/* 234 */     user.setRegisterIp(ip);
/* 235 */     user.setRegisterTime(now);
/* 236 */     user.setLastLoginIp(ip);
/* 237 */     user.setLastLoginTime(now);
/* 238 */     user.setActivation(activation);
/* 239 */     user.init();
/* 240 */     this.dao.save(user);
/* 241 */     if (!activation.booleanValue()) {
/* 242 */       String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
/* 243 */       user.setActivationCode(uuid);
/* 244 */       senderEmail(username, email, uuid, sender, msgTpl);
/*     */     }
/* 246 */     return user;
/*     */   }
/*     */ 
/*     */   public UnifiedUser update(Integer id, String password, String email)
/*     */   {
/* 253 */     UnifiedUser user = findById(id);
/* 254 */     if (!StringUtils.isBlank(email))
/* 255 */       user.setEmail(email);
/*     */     else {
/* 257 */       user.setEmail(null);
/*     */     }
/* 259 */     if (!StringUtils.isBlank(password)) {
/* 260 */       user.setPassword(this.pwdEncoder.encodePassword(password));
/*     */     }
/* 262 */     return user;
/*     */   }
/*     */ 
/*     */   public boolean isPasswordValid(Integer id, String password) {
/* 266 */     UnifiedUser user = findById(id);
/* 267 */     return this.pwdEncoder.isPasswordValid(user.getPassword(), password);
/*     */   }
/*     */ 
/*     */   public UnifiedUser deleteById(Integer id) {
/* 271 */     UnifiedUser bean = this.dao.deleteById(id);
/* 272 */     return bean;
/*     */   }
/*     */ 
/*     */   public UnifiedUser[] deleteByIds(Integer[] ids) {
/* 276 */     UnifiedUser[] beans = new UnifiedUser[ids.length];
/* 277 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 278 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 280 */     return beans;
/*     */   }
/*     */ 
/*     */   public UnifiedUser active(String username, String activationCode) {
/* 284 */     UnifiedUser bean = getByUsername(username);
/* 285 */     bean.setActivation(Boolean.valueOf(true));
/* 286 */     bean.setActivationCode(null);
/* 287 */     return bean;
/*     */   }
/*     */ 
/*     */   public UnifiedUser activeLogin(UnifiedUser user, String ip) {
/* 291 */     updateLoginSuccess(user.getId(), ip);
/* 292 */     return user;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setConfigMng(ConfigMng configMng)
/*     */   {
/* 301 */     this.configMng = configMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setPwdEncoder(PwdEncoder pwdEncoder) {
/* 306 */     this.pwdEncoder = pwdEncoder;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(UnifiedUserDao dao) {
/* 311 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.impl.UnifiedUserMngImpl
 * JD-Core Version:    0.6.0
 */